package com.shree.intergration.common.web.http;

import lombok.Data;
import org.apache.http.cookie.Cookie;

import java.util.List;
import java.util.Map;

/**
 * 封装的HTTP响应参数类
 *
 * @author Darkprayer
 */
@Data
public class KlzHttpResponse {

    /**
     * 响应HTTP状态替代码
     */
    protected int statusCode;

    /**
     * 响应的Cookies
     */
    protected List<Cookie> cookies;

    /**
     * 响应的Header
     */
    protected Map<String, String> headers;

    /**
     * 响应的内容
     */
    protected String content;

    /**
     * 响应的文件流
     */
    protected byte[] file;
}
