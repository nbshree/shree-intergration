package com.shree.intergration.common.web.http;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.*;

public class KlzHttpClient {

    public static final String DEFAULT_ENCODING = "UTF-8" ;
    public static final String REQ_HTTP = "http" ;
    public static final String REQ_HTTPS = "https" ;
    public static final String REQ_METHOD_GET = "get" ;
    public static final String REQ_METHOD_POST = "post" ;
    public static final String REQ_METHOD_PUT = "put" ;
    public static final String REQ_METHOD_DELETE = "delete" ;
    public static final String REQ_TYPE_JSON = "json" ;

    private static final String CONTENT_TYPE = "Content-type" ;
    private static final String APPLICATION_JSON = "application/json" ;

    /**
     * 设置连接超时时间，单位毫秒。
     */
    protected int CONNECT_TIMEOUT = 30000;

    /**
     * 请求获取数据的超时时间(即响应时间)，单位毫秒。
     */
    protected int SOCKET_TIMEOUT = 30000;

    /**
     * 连接管理器
     */
    protected PoolingHttpClientConnectionManager connectionManager;

    /**
     * 请求配置
     */
    protected RequestConfig requestConfig;

    /**
     * 初始化是否成功
     */
    protected boolean initialized = false;

    public KlzHttpClient() {
        try {
            // 绕过验证的方式处理https请求
            SSLContext sslContext = this.createIgnoreVerifySsl();
            // 设置协议http和https对应的处理socket链接工厂的对象
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register(REQ_HTTP, PlainConnectionSocketFactory.INSTANCE)
                    .register(REQ_HTTPS, new SSLConnectionSocketFactory(sslContext))
                    .build();
            // 初始化连接管理器
            connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            connectionManager.setMaxTotal(240);
            connectionManager.setDefaultMaxPerRoute(24);
            // 设置请求超时时间
            requestConfig = RequestConfig.custom()
                    .setSocketTimeout(SOCKET_TIMEOUT)
                    .setConnectTimeout(CONNECT_TIMEOUT)
                    .setConnectionRequestTimeout(SOCKET_TIMEOUT)
                    .build();
            this.initialized = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 创建绕过验证https请求
     *
     * @return SSLContext
     * @throws NoSuchAlgorithmException 没有对应加密方法
     * @throws KeyManagementException   没有对应Key管理
     */
    public SSLContext createIgnoreVerifySsl() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = SSLContext.getInstance("SSLv3");
        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        // HTTPS请求初始化
        sc.init(null, new TrustManager[]{trustManager}, null);
        return sc;
    }


    /**
     * 创建一个HttpClient
     *
     * @return HttpClient
     */
    public CloseableHttpClient createHttpClient(CookieStore cookieStore) {
        if (this.initialized) {
            HttpClientBuilder httpClientBuilder = HttpClients.custom()
                    // 设置连接池管理
                    .setConnectionManager(connectionManager)
                    // 设置共享连接池
                    .setConnectionManagerShared(true)
                    // 设置请求配置
                    .setDefaultRequestConfig(requestConfig)
                    // 设置失败不重试
                    .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false));

            if (null != cookieStore) {
                // 添加Cookie
                httpClientBuilder.setDefaultCookieStore(cookieStore);
            }
            return httpClientBuilder.build();
        } else {
            return null;
        }
    }

    /**
     * Description: 释放资源
     *
     * @param httpResponse HTTP响应
     * @param httpClient   HTTP客户端
     * @throws IOException 释放时的错误
     */
    public void release(CloseableHttpResponse httpResponse, CloseableHttpClient httpClient) throws IOException {
        // 释放资源
        if (null != httpResponse) {
            httpResponse.close();
        }
        if (null != httpClient) {
            httpClient.close();
        }
    }

    /**
     * 发送一个HTTP请求
     *
     * @param expRequest HTTP请求参数
     * @return HTTP请求响应
     * @throws Exception 错误
     */
    public KlzHttpResponse doSendRequest(KlzHttpRequest expRequest) throws Exception {
        // 基础请求对象
        HttpRequestBase request = null;

        // 请求的Cookie
        CookieStore cookieStore = new BasicCookieStore();
        // 设置HTTP请求Cookies
        if (null != expRequest && expRequest.getCookies().size() > 0) {
            for (Cookie cookie : expRequest.getCookies()) {
                cookieStore.addCookie(cookie);
            }
        }
        // 创建httpClient对象
        CloseableHttpClient httpClient = this.createHttpClient(cookieStore);

        // 根据请求方法构建Request
        String requestMethod = expRequest.getMethod().toLowerCase();
        if (REQ_METHOD_GET.equals(requestMethod) || REQ_METHOD_DELETE.equals(requestMethod)) {
            request = createGetRequest(expRequest);
        } else if (REQ_METHOD_POST.equals(requestMethod) || REQ_METHOD_PUT.equals(requestMethod)) {
            request = createPostRequest(expRequest);
        }

        // 判断是否成功构建Request
        if (null != request) {
            // 设置HTTP请求头
            if (null != expRequest.getHeaders() && expRequest.getHeaders().size() > 0) {
                Set<Map.Entry<String, String>> entrySet = expRequest.getHeaders().entrySet();
                for (Map.Entry<String, String> entry : entrySet) {
                    // 设置到请求头到HttpRequestBase对象中
                    request.setHeader(entry.getKey(), entry.getValue());
                }
            }
            // 设置HTTP请求的配置
            if (null == expRequest.getRequestConfig()) {
                request.setConfig(requestConfig);
            } else {
                request.setConfig(expRequest.getRequestConfig());
            }
            // 创建HttpResponse对象
            CloseableHttpResponse response = null;
            try {
                // 尝试执行请求并获得响应结果
                response = httpClient.execute(request);
                // 获取返回结果
                if (null != response && null != response.getStatusLine()) {
                    KlzHttpResponse expResponse = new KlzHttpResponse();
                    // 设定响应的状态代码
                    expResponse.setStatusCode(response.getStatusLine().getStatusCode());
                    // 设定响应的Cookie
                    expResponse.setCookies(cookieStore.getCookies());
                    // 设定响应的头
                    Map<String, String> headers = new HashMap<>();
                    for (Header header : response.getAllHeaders()) {
                        headers.put(header.getName(), header.getValue());
                    }
                    expResponse.setHeaders(headers);
                    // 设定响应的内容
                    HttpEntity httpEntity = response.getEntity();
                    if (null != httpEntity) {
                        // 如果是文件设定文件内容
                        if (expRequest.isFileResponse()) {
                            InputStream is = httpEntity.getContent();
                            byte[] bytes = new byte[is.available()];
                            is.read(bytes);
                            expResponse.setFile(bytes);
                            is.close();
                        } else {
                            // 否则设定字符串内容
                            expResponse.setContent(EntityUtils.toString(httpEntity, DEFAULT_ENCODING));
                        }
                    }
                    return expResponse;
                } else {
                    return null;
                }
            } finally {
                // 释放资源
                release(response, httpClient);
            }
        } else {
            return null;
        }
    }

    /**
     * Get与Delete的基础请求构建
     *
     * @param requestParams Request参数
     * @return 构建的Request
     * @throws Exception 构建时的错误
     */
    private HttpRequestBase createGetRequest(KlzHttpRequest requestParams) throws Exception {
        // 创建请求对象
        HttpRequestBase request = null;
        // 创建访问的地址
        URIBuilder uriBuilder = new URIBuilder(requestParams.getUrl());
        // Get与Delete将参数拼入Url
        if (null != requestParams.getParameters() && requestParams.getParameters().size() > 0) {
            Set<Map.Entry<String, String>> entrySet = requestParams.getParameters().entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue());
            }
        }
        // 构建Get或者Delete请求
        if (("get").equals(requestParams.getMethod().toLowerCase())) {
            request = new HttpGet(uriBuilder.build());
        } else if (("delete").equals(requestParams.getMethod().toLowerCase())) {
            request = new HttpDelete(uriBuilder.build());
        }
        return request;
    }

    /**
     * Post与Put的基础请求构建
     *
     * @param expRequest Request参数
     * @return 构建的Request
     * @throws Exception 构建时的错误
     */
    private HttpEntityEnclosingRequestBase createPostRequest(KlzHttpRequest expRequest) throws Exception {
        // 创建请求对象
        HttpEntityEnclosingRequestBase request = null;
        // 创建访问的地址
        URIBuilder uriBuilder = new URIBuilder(expRequest.getUrl());
        String requestMethod = expRequest.getMethod().toLowerCase();
        // 构建Post或者Put请求
        if (REQ_METHOD_POST.equals(requestMethod)) {
            request = new HttpPost(uriBuilder.build());
        } else if (REQ_METHOD_PUT.equals(requestMethod)) {
            request = new HttpPut(uriBuilder.build());
        }
        // 判断请求类型是不是JSON提交
        if (REQ_TYPE_JSON.equals(expRequest.getType().toLowerCase())) {
            if (null != expRequest.getJsonData()) {
                StringEntity jsonEntity = new StringEntity(expRequest.getJsonData(), DEFAULT_ENCODING);
                jsonEntity.setContentEncoding(DEFAULT_ENCODING);
                request.setEntity(jsonEntity);
            }
            request.setHeader(CONTENT_TYPE, APPLICATION_JSON);
        } else {
            // 默认以表单方式填充参数
            if (null != expRequest.getParameters() && expRequest.getParameters().size() > 0) {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                Set<Map.Entry<String, String>> entrySet = expRequest.getParameters().entrySet();
                for (Map.Entry<String, String> entry : entrySet) {
                    nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                // 设置到请求的http对象中
                request.setEntity(new UrlEncodedFormEntity(nameValuePairs, DEFAULT_ENCODING));
            }
        }
        return request;
    }
}
