package com.shree.intergration.shiro;

import com.shree.intergration.common.util.JacksonUtils;
import com.shree.intergration.common.web.rest.RestResult;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Log4j2
@ControllerAdvice
public class ShiroExceptionHandler {

    private static final String NOT_LOGGED_IN = JacksonUtils.getJson(RestResult.createFailedResult("400", null, "Not logged in."));

    private static final String NOT_AUTHORIZED = JacksonUtils.getJson(RestResult.createFailedResult("403", null, "No authorized."));


    /**
     * 登录认证异常
     */
    @ExceptionHandler({UnauthenticatedException.class, AuthenticationException.class})
    public String authenticationException(HttpServletRequest request, HttpServletResponse response) {
        if (isAjaxRequest(request, response)) {
            writeJson(NOT_LOGGED_IN, response);
            return null;
        } else {
            return "redirect:/login";
        }
    }

    /**
     * 权限异常
     */
    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
    public String authorizationException(HttpServletRequest request, HttpServletResponse response) {
        if (isAjaxRequest(request, response)) {
            writeJson(NOT_AUTHORIZED, response);
            return null;
        } else {
            return "redirect:/error/403";
        }
    }

    private void writeJson(String jsonStr, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            out = response.getWriter();
            out.write(jsonStr);
        } catch (IOException ex) {
            log.error("授权拦截输出错误！", ex);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public boolean isAjaxRequest(HttpServletRequest request, HttpServletResponse response) {
        String requestedWith = request.getHeader("X-Requested-With");
        if (!StringUtils.isEmpty(requestedWith) && "XMLHttpRequest".equalsIgnoreCase(requestedWith)) {
            return true;
        } else {
            return false;
        }
    }
}
