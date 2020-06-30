package com.shree.intergration.common.web.session;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public abstract class UserSession {

    /**
     * 会话ID
     */
    protected String sessionId;

    /**
     * 用户ID
     */
    protected String userId;

    /**
     * 用户名
     */
    protected String userName;

    /**
     * 用户昵称
     */
    protected String nickName;

    /**
     * 扩展数据
     */
    protected Map<String, Object> extendMap;

    public UserSession() {
        this.extendMap = new HashMap<>();
    }
}
