package com.shree.intergration.common.web.session;

/**
 * 单机版简易HttpSession提供器接口, 所有 UserSession 提供器都需要实现该接口工作
 * 后续版本里还需要提供 shiro 的接口, 或是集成 redis 的接口
 *
 * @author Victor
 */
public interface UserSessionProvider {

    void setDebugMode(boolean isDebug);

    /**
     * 获取 UserSession
     *
     * @return 用户Session
     */
    <TUserSession extends UserSession> TUserSession getUserSession();

    /**
     * 设置 UserSession
     *
     * @param userSession 用户Session
     */
    <TUserSession extends UserSession> void setUserSession(TUserSession userSession);
}
