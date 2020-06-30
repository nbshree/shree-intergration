package com.shree.intergration.common.web.http;

import lombok.Data;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.cookie.Cookie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 封装的HTTP请求参数类
 *
 * @author Darkprayer
 */
@Data
public class KlzHttpRequest {

    /**
     * 请求提交方式 form|json
     */
    protected String type = "form" ;

    /**
     * 请求方法 get|post|put|delete
     */
    protected String method = "get" ;

    /**
     * 请求Url
     */
    protected String url;

    /**
     * 请求附带的Cookie
     */
    protected List<Cookie> cookies;

    /**
     * 请求附带Header
     */
    protected Map<String, String> headers;

    /**
     * 请求附带参数（type为form时使用）
     */
    protected Map<String, String> parameters;

    /**
     * 请求附带JSON数据（type为json时使用）
     */
    protected String jsonData;

    /**
     * 请求提交的数据是否为文件
     */
    protected boolean fileRequest = false;

    /**
     * 请求返回的数据是否为文件
     */
    protected boolean fileResponse = false;

    /**
     * HttpClient的请求配置
     */
    protected RequestConfig requestConfig;

    public KlzHttpRequest() {
        this.setType("form");
        this.setMethod("get");
        this.setFileRequest(false);
        this.setFileResponse(false);
        this.setCookies(new ArrayList<>());
        this.setHeaders(new HashMap<>());
        this.setParameters(new HashMap<>());
        this.setJsonData(null);
        this.setRequestConfig(null);
    }

    public KlzHttpRequest(String url, String method, Map<String, String> parameters) {
        this();
        this.setUrl(url);
        this.setMethod(method);
        this.setParameters(parameters);
    }
}
