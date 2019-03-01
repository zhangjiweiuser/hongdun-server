package com.hongdun.controller;

import javax.servlet.http.HttpServletRequest;

import com.hongdun.auth.AuthCode;
import com.hongdun.entity.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录
 *
 * @author zhang
 * @date 2019-03-01 下午 14:54
 */
@RestController
public class LoginController {

    @GetMapping("/login")
    public Response login(HttpServletRequest request, String name, String password) {
        if ("zhangsan".equals(name) && "zhangsan".equals(password)) {
            request.getSession().setAttribute("permission", AuthCode.ADDUSER);
            request.getSession().setAttribute("user", "zhang");
            return Response.success(0, "登录成功");
        } else {
            return Response.success(-1, "账号密码不对");
        }
    }

    @GetMapping("/login2")
    public Response login(HttpServletRequest request) {
        return Response.success(0, request.getSession().getAttribute("user").toString());
    }
}
