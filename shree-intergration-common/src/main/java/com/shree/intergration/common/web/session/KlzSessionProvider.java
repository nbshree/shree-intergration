package com.shree.intergration.common.web.session;

import com.shree.intergration.common.web.util.CookieUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class KlzSessionProvider implements UserSessionProvider {

    /**
     * 默认的SESSION名称
     */
    public static final String DEFAULT_SESSION_NAME = "ExplorerSession";

    /**
     * 默认的COOKIE名称
     */
    public static final String DEFAULT_AUTH_SESSION = "ExplorerAuthSession";

    /**
     * 默认的PERMISSION名称
     */
    public static final String DEFAULT_PERMISSION_NAME = "ExplorerPermissions";

    protected KlzSession debugSession;

    @Getter
    @Setter
    protected boolean debugMode;

    public KlzSessionProvider() {
        this.debugMode = false;
        this.debugSession = new KlzSession();
        this.debugSession.setUserId("000000");
        this.debugSession.setUserName("ExpDebugger");
        this.debugSession.setNickName("ExpDebugger");
    }

    public KlzSession getUserSession() {
        if (this.debugMode) {
            return this.debugSession;
        } else {
            KlzSession userSession = null;
            ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (null != sra) {
                HttpServletRequest request = sra.getRequest();
                if (null != request.getSession()) {
                    HttpSession httpSession = request.getSession();
                    userSession = (KlzSession) httpSession.getAttribute(DEFAULT_SESSION_NAME);
                }
            }
            return userSession;
        }
    }

    public void setUserSession(UserSession userSession) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null != sra) {
            HttpServletRequest request = sra.getRequest();
            if (null != request.getSession()) {
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute(DEFAULT_SESSION_NAME, userSession);
            }
            HttpServletResponse response = sra.getResponse();
            if (null != response) {
                CookieUtils.addCookie(response, "", "/", DEFAULT_AUTH_SESSION, userSession.getSessionId(), 7200, false, false);
            }
        }

    }
}
