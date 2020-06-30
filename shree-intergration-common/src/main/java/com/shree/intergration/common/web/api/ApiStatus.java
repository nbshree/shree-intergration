package com.shree.intergration.common.web.api;

public enum ApiStatus {
    OK("200", "OK"),                                                // 请求正确执行
    NO_CONTENT("20411", "No Suited Content"),                       // 执行成功，但没有合适的结果

    INVALID_REQUEST("400", "Invalid Request!"),                     // 无效的请求
    INVALID_REQUEST_PARAMS("40011", "Invalid Request Parameters!"), // 无效的请求参数
    INVALID_CLIENT("40012", "Invalid Client!"),                     // 无效的客户端
    INVALID_ACCESS_TOKEN("40013", "Invalid Access Token!"),         // 无效的AccessToken

    FAILED("40211", "Request Process Failed!"),                     // 请求执行完成，但中间部分逻辑失败

    UNAUTHORIZED_REQUEST("403", "Unauthorized Request!"),           // 未授权的请求
    EXPIRED_CLIENT_GRANT("40311", "Client Grant Expired!"),         // 客户端授权过期
    DISABLED_CLIENT("40312", "Client Disabled!"),                   // 已禁用的客户端

    ERROR("500", "Service Error!");                                 // 服务内部故障

    private final String code;
    private final String description;

    ApiStatus(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }
}


