package com.hongdun.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hongdun.auth.AuthCode;
import com.hongdun.auth.AuthPermission;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author jiwei.zhang
 * @date 2019-03-01 下午 15:04
 */
public class PermissionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return userAuthValidate(request, handler);
    }

    private boolean userAuthValidate(HttpServletRequest request, Object handler) {
        AuthPermission permission = ((HandlerMethod) handler).getMethodAnnotation(AuthPermission.class);
        if (permission == null) {
            return true;
        }
        AuthCode[] perms = permission.perms();

        String funcCode = permission.value().getAuthCode();
        AuthCode authCode = (AuthCode) request.getSession().getAttribute("permission");
        if(null == authCode){
            return false;
        }
        if (funcCode.equals(authCode.getAuthCode())) {
            return true;
        }
        return false;
    }
}
