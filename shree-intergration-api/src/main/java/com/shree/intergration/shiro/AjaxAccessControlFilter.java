package com.shree.intergration.shiro;

import lombok.extern.log4j.Log4j2;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
public class AjaxAccessControlFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginRequest(request, response)) {
            return true;
        } else {
            Subject subject = getSubject(request, response);
            // If principal is not null, then the user is known and should be
            // allowed access.
            return subject.getPrincipal() != null;
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        try {
            String ajaxReqHeader = ((HttpServletRequest) request).getHeader("X-Requested-With");
            if (!"XMLHttpRequest".equalsIgnoreCase(ajaxReqHeader)) {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                if (httpResponse.getHeader("Content-Type").toLowerCase().contains("json")) {
                    response.getWriter().write("{\"status\": 0,\"message\": \"not login yet!\"}");
                }
            }
            saveRequestAndRedirectToLogin(request, response);
        } catch (Exception ex) {
            log.error("AjaxAccessControlFilter发生错误！", ex);
            saveRequestAndRedirectToLogin(request, response);
        }
        return false;
    }
}

