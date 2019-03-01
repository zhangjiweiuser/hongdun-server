package com.hongdun.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;

import com.github.cage.Cage;
import com.hongdun.entity.Response;
import com.hongdun.util.TokenGenerator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;


/**
 * Created on 2019/2/28.
 *
 * @author zhang
 */
@RestController
@Validated
public class VerifyCodeController {

    private static final Logger logger = LoggerFactory.getLogger(VerifyCodeController.class);

    private static final Cage cage = new Cage(null, null, null, null, null, new TokenGenerator(), null);


    public static void generateToken(HttpSession session) {
        String token = cage.getTokenGenerator().next();
        session.setAttribute("captchaToken", token);
        markTokenUsed(session, false);
    }

    public static String getToken(HttpSession session) {
        Object val = session.getAttribute("captchaToken");

        return val != null ? val.toString() : null;
    }

    protected static void markTokenUsed(HttpSession session, boolean used) {
        session.setAttribute("captchaTokenUsed", used);
    }

    protected static boolean isTokenUsed(HttpSession session) {
        return !Boolean.FALSE.equals(session.getAttribute("captchaTokenUsed"));
    }

    protected void setResponseHeaders(HttpServletResponse resp) {
        resp.setContentType("image/" + cage.getFormat());
        resp.setHeader("Cache-Control", "no-cache, no-store");
        resp.setHeader("Pragma", "no-cache");
        long time = System.currentTimeMillis();
        resp.setDateHeader("Last-Modified", time);
        resp.setDateHeader("Date", time);
        resp.setDateHeader("Expires", time);
    }

    /**
     * 生成验证码
     */
    @RequestMapping(value = "/generateCode")
    public void generateCode(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        generateToken(session);
        String token = getToken(session);
        logger.info("生成验证码时session为:{},验证码为:{}", session.toString(), token);
        if (token == null || isTokenUsed(session)) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Captcha not found.");
            return;
        }
        setResponseHeaders(resp);
        markTokenUsed(session, true);
        cage.draw(token, resp.getOutputStream());
    }

    /**
     * 校验验证码
     */
    @RequestMapping("/verifyCode")
    public Object verifyCode(HttpServletRequest request, @NotBlank(message = "验证码输入为空，请输入正确验证码!") String code) {

        logger.info("verifyCode session是否超时:{}", (null == request.getSession(false)));
        String verifyCode = (String) WebUtils.getSessionAttribute(request, "captchaToken");
        logger.info("verifyCode验证码为:{},用户传code为:{}", verifyCode, code);

        if (StringUtils.isBlank(verifyCode)) {
            return Response.error(-1, "验证码输入为空，请输入正确验证码!");
        }

        if (!verifyCode.equalsIgnoreCase(code)) {
            return Response.error(-1, "验证码输入错误，请重新输入");
        }

        return Response.success("验证码输入正确");

    }
}
