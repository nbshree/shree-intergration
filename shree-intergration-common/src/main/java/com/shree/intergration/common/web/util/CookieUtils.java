package com.shree.intergration.common.web.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie工具类
 *
 * @author Darkprayer
 */
public class CookieUtils {

    /**
     * 浏览器关闭时自动删除状态值
     */
    public final static int CLEAR_BROWSER_IS_CLOSED = -1;

    /**
     * 立即删除状态值
     */
    public final static int CLEAR_IMMEDIATELY_REMOVE = 0;

    /**
     * 根据cookieName获取Cookie
     *
     * @param request    Http请求
     * @param cookieName Cookie名字
     * @return Cookie 相应的Cookie
     */
    public static Cookie getCookieByName(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals(cookieName)) {
                return cookies[i];
            }
        }
        return null;
    }

    /**
     * 清除指定Cookie 等同于 clearCookieByName(...)
     * 该方法不判断Cookie是否存在,因此不对外暴露防止Cookie不存在异常.
     *
     * @param response   Http响应
     * @param cookieName cookie name
     * @param domain     Cookie所在的域
     * @param path       Cookie 路径
     * @return boolean 是否清除成功
     */
    private static boolean clearCookie(HttpServletResponse response, String cookieName, String domain, String path) {
        boolean result = false;
        try {
            Cookie cookie = new Cookie(cookieName, "");
            cookie.setMaxAge(CLEAR_IMMEDIATELY_REMOVE);
            cookie.setDomain(domain);
            cookie.setPath(path);
            response.addCookie(cookie);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据cookieName清除所有域下的指定Cookie
     *
     * @param request    Http请求
     * @param response   Http响应
     * @param cookieName Cookie名称
     * @param domain     Cookie所在的域
     * @param path       Cookie路径
     * @return boolean 是否清除成功
     */
    public static boolean clearCookieByName(HttpServletRequest request, HttpServletResponse response, String cookieName, String domain, String path) {
        boolean result = false;
        Cookie ck = getCookieByName(request, cookieName);
        if (ck != null) {
            result = clearCookie(response, cookieName, domain, path);
        }
        return result;
    }

    /**
     * 根据cookieName清空【默认域下】的Cookie
     *
     * @param response   Http响应
     * @param cookieName Cookie名称
     */
    public static void clearCookieByName(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, "");
        cookie.setMaxAge(CLEAR_IMMEDIATELY_REMOVE);
        response.addCookie(cookie);
    }

    /**
     * 清除指定domain的所有Cookie
     *
     * @param request  Http请求
     * @param response Http响应
     * @param domain   Cookie所在的域
     * @param path     Cookie 路径
     */
    public static void clearAllCookies(HttpServletRequest request, HttpServletResponse response, String domain, String path) {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            clearCookie(response, cookies[i].getName(), domain, path);
        }
    }

    /**
     * 添加 Cookie
     *
     * @param response Http响应
     * @param domain   所在域
     * @param path     域名路径
     * @param name     名称
     * @param value    内容
     * @param maxAge   生命周期参数
     * @param httpOnly 只读
     * @param secured  Https协议下安全传输
     */
    public static void addCookie(HttpServletResponse response, String domain, String path, String name, String value, int maxAge, boolean httpOnly, boolean secured) {
        Cookie cookie = new Cookie(name, value);
        // 不设置该参数默认 当前所在域
        if (domain != null && !"".equals(domain)) {
            cookie.setDomain(domain);
        }
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);

        // Cookie 只在Https协议下传输设置
        if (secured) {
            cookie.setSecure(secured);
        }

        // Cookie Http只读设置
        if (httpOnly) {
            addHttpOnlyCookie(response, cookie);
        } else {
            /*
             * //servlet 3.0 support
             * cookie.setHttpOnly(httpOnly);
             */
            response.addCookie(cookie);
        }

    }

    /**
     * 添加 Cookie
     * (解决 servlet 3.0 以下版本不支持 HttpOnly)
     *
     * @param response HttpServletResponse类型的响应
     * @param cookie   要设置httpOnly的cookie对象
     */
    public static void addHttpOnlyCookie(HttpServletResponse response, Cookie cookie) {
        if (cookie == null) {
            return;
        }
        //依次取得cookie中的名称、值、 最大生存时间、路径、域和是否为安全协议信息
        String cookieName = cookie.getName();
        String cookieValue = cookie.getValue();
        int maxAge = cookie.getMaxAge();
        String path = cookie.getPath();
        String domain = cookie.getDomain();
        boolean isSecure = cookie.getSecure();
        StringBuffer sf = new StringBuffer();
        sf.append(cookieName + "=" + cookieValue + ";");

        if (maxAge >= 0) {
            sf.append("Max-Age=" + cookie.getMaxAge() + ";");
        }

        if (domain != null) {
            sf.append("domain=" + domain + ";");
        }

        if (path != null) {
            sf.append("path=" + path + ";");
        }

        if (isSecure) {
            sf.append("secure;HTTPOnly;");
        } else {
            sf.append("HTTPOnly;");
        }

        response.addHeader("Set-Cookie", sf.toString());
    }
}
