package com.shree.intergration.common.web.util;

import com.shree.intergration.common.util.FieldUtils;
import com.shree.intergration.common.util.JacksonUtils;
import com.shree.intergration.common.web.http.KlzHttpClient;
import com.shree.intergration.common.web.http.KlzHttpRequest;
import com.shree.intergration.common.web.http.KlzHttpResponse;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Http客户端模拟工具
 *
 * @author Darkprayer
 */
@Log4j2
public class HttpClientUtils {

    @Getter
    protected static final KlzHttpClient expHttpClient = new KlzHttpClient();

    /**
     * 以提交表单的方式发送一个HTTP请求
     *
     * @param url         请求URL
     * @param method      请求方法 GET / POST
     * @param parameters  请求参数
     * @param fileRequest 是否为文件请求
     * @return Explorer格式的Response
     * @throws Exception 抛出的异常
     */
    public static KlzHttpResponse submitRequest(String url, String method, Map<String, String> parameters, boolean fileRequest) throws Exception {
        KlzHttpRequest expRequest = new KlzHttpRequest(url, method, parameters);
        expRequest.setFileResponse(fileRequest);
        return getExpHttpClient().doSendRequest(expRequest);
    }

    /**
     * 以提交表单的方式发送一个HTTP请求
     *
     * @param url        请求URL
     * @param method     请求方法
     * @param parameters 请求参数
     * @return String型的响应Content
     * @throws Exception 抛出的异常
     */
    public static String submitFormRequest(String url, String method, Map<String, String> parameters) throws Exception {
        KlzHttpResponse expResponse = submitRequest(url, method, parameters, false);
        return expResponse.getContent();
    }

    /**
     * 发送一个表单方式提交的HTTP请求
     *
     * @param url        请求URL
     * @param method     请求方法
     * @param parameters 请求参数
     * @param returnType 返回泛型的目标类型
     * @param <T>        返回泛型对象
     * @return Json解析后的对象
     * @throws Exception 抛出的异常
     */
    public static <T> T submitFormRequest(String url, String method, Map<String, String> parameters, Class<T> returnType) throws Exception {
        String resultJson = submitFormRequest(url, method, parameters);
        return JacksonUtils.parseJson(resultJson, returnType);
    }

    /**
     * 以提交表单的方式发送一个获取文件的HTTP请求
     *
     * @param url        请求URL
     * @param method     请求方法 GET / POST
     * @param parameters 请求参数
     * @return 文件bytes
     * @throws Exception 抛出的异常
     */
    public static byte[] submitFormRequestForFile(String url, String method, Map<String, String> parameters) throws Exception {
        KlzHttpResponse expResponse = submitRequest(url, method, parameters, true);
        return expResponse.getFile();
    }


    /**
     * 发送一个表单模式提交的Get请求
     *
     * @param url        请求URL
     * @param parameters 请求参数
     * @return String型的响应Content
     */
    public static String doGet(String url, Map<String, String> parameters) {
        try {
            return submitFormRequest(url, KlzHttpClient.REQ_METHOD_GET, parameters);
        } catch (Exception ex) {
            log.error("表单模式提交的Get请求发生错误！", ex);
            return null;
        }
    }

    /**
     * 发送一个表单模式提交的Get请求
     *
     * @param url        请求URL
     * @param parameters 请求参数
     * @param returnType 返回泛型的目标类型
     * @param <T>        返回泛型对象
     * @return Json解析后的对象
     */
    public static <T> T doGet(String url, Map<String, String> parameters, Class<T> returnType) {
        try {
            return submitFormRequest(url, KlzHttpClient.REQ_METHOD_GET, parameters, returnType);
        } catch (Exception ex) {
            log.error("表单模式提交的Get请求发生错误！", ex);
            return null;
        }
    }

    /**
     * 发送一个表单模式提交的Get请求获取文件
     *
     * @param url        请求URL
     * @param parameters 请求参数
     * @return 文件bytes
     */
    public static byte[] doGetBytes(String url, Map<String, String> parameters) {
        try {
            return submitFormRequestForFile(url, KlzHttpClient.REQ_METHOD_GET, parameters);
        } catch (Exception ex) {
            log.error("表单模式提交的Get文件请求发生错误！", ex);
            return null;
        }
    }

    /**
     * 发送一个表单模式提交的Post请求
     *
     * @param url        请求URL
     * @param parameters 请求参数
     * @return String型的响应Content
     */
    public static String doPost(String url, Map<String, String> parameters) {
        try {
            return submitFormRequest(url, KlzHttpClient.REQ_METHOD_POST, parameters);
        } catch (Exception ex) {
            log.error("表单模式提交的Post请求发生错误！", ex);
            return null;
        }
    }

    /**
     * 发送一个表单模式提交的Post请求
     *
     * @param url        请求URL
     * @param parameters 请求参数
     * @param returnType 返回泛型的目标类型
     * @param <T>        返回泛型对象
     * @return Json解析后的对象
     */
    public static <T> T doPost(String url, Map<String, String> parameters, Class<T> returnType) {
        try {
            return submitFormRequest(url, KlzHttpClient.REQ_METHOD_POST, parameters, returnType);
        } catch (Exception ex) {
            log.error("表单模式提交的Post请求发生错误！", ex);
            return null;
        }
    }

    /**
     * 发送一个表单模式提交的Post请求
     *
     * @param url  请求URL
     * @param data 请求参数, 实体对象
     * @return String型的响应Content
     */
    public static String doPost(String url, Object data) {
        try {
            Field[] fields = FieldUtils.getFieldArray(data.getClass(), true);
            Map<String, String> bodyParams = new HashMap<String, String>();
            for (Field field : fields) {
                Object value = FieldUtils.getFieldValue(data, field.getName());
                if (null != value) {
                    bodyParams.put(field.getName(), value.toString());
                }
            }
            return doPost(url, bodyParams);
        } catch (Exception ex) {
            log.error("表单模式提交的Post请求发生错误！", ex);
            return null;
        }
    }

    /**
     * 发送一个表单模式提交的Post请求
     *
     * @param url        请求URL
     * @param data       请求参数对象
     * @param returnType 返回泛型的目标类型
     * @param <T>        返回泛型对象
     * @return Json解析后的对象
     */
    public static <T> T doPost(String url, Object data, Class<T> returnType) {
        try {
            String resultJson = doPost(url, data);
            if (null != resultJson) {
                return JacksonUtils.parseJson(resultJson, returnType);
            } else {
                return null;
            }
        } catch (Exception ex) {
            log.error("表单模式提交的Post请求发生错误！", ex);
            return null;
        }
    }

    /**
     * 发送一个Post请求, 以Body内填充JSON的模式提交参数
     *
     * @param url  请求URL
     * @param data 请求发送的对象实体
     * @return String型的响应Content
     */
    public static String doPostJson(String url, Object data) {
        try {
            String jsonData = JacksonUtils.getJson(data);
            KlzHttpRequest expRequest = new KlzHttpRequest(url, "post", null);
            expRequest.setType(KlzHttpClient.REQ_TYPE_JSON);
            expRequest.setJsonData(jsonData);
            expRequest.setFileResponse(false);
            KlzHttpResponse responseParams = getExpHttpClient().doSendRequest(expRequest);
            return responseParams.getContent();
        } catch (Exception ex) {
            log.error("Body模式提交的PostJson请求发生错误！", ex);
            return null;
        }
    }

    /**
     * 发送一个Post请求, 以Body内填充JSON的模式提交参数
     *
     * @param url        请求URL
     * @param data       请求发送的对象实体
     * @param returnType 返回泛型的目标类型
     * @param <T>        返回泛型对象
     * @return Json解析后的对象
     */
    public static <T> T doPostJson(String url, Object data, Class<T> returnType) {
        try {
            String resultJson = doPostJson(url, data);
            if (null != resultJson) {
                return JacksonUtils.parseJson(resultJson, returnType);
            } else {
                return null;
            }
        } catch (Exception ex) {
            log.error("Body模式提交的PostJson请求发生错误！", ex);
            return null;
        }
    }
}
