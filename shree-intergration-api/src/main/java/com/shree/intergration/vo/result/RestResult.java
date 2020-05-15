package com.shree.intergration.vo.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RestResult<TData> extends BaseResult {
    protected TData data;

    protected Object extendData;

    public RestResult() {
        this.setStatus(SUCCESS);
    }

    /**
     * 设置一个返回结果
     *
     * @param status  返回的状态
     * @param message 返回的消息
     */
    public void setResult(int status, String message) {
        this.setStatus(status);
        this.setMessage(message);
    }

    /**
     * 设置一个返回结果
     *
     * @param status  返回的状态
     * @param message 返回的消息
     * @param data    返回的数据
     */
    public void setResult(int status, String message, TData data) {
        this.setResult(status, message);
        this.setData(data);
    }

    /**
     * 设置一个返回结果
     *
     * @param status     返回的状态
     * @param message    返回的消息
     * @param data       返回的数据
     * @param extendData 附加的数据
     */
    public void setResult(int status, String message, TData data, Object extendData) {
        this.setResult(status, message, data);
        this.setExtendData(extendData);
    }

    public void setSuccessResult(String message) {
        this.setResult(SUCCESS, message);
    }

    public void setSuccessResult(String message, TData data) {
        this.setResult(SUCCESS, message, data);
    }

    public void setSuccessResult(String message, TData data, Object extendData) {
        this.setResult(SUCCESS, message, data, extendData);
    }

    public void setFailedResult(String message) {
        this.setResult(FAILED, message);
    }

    public void setFailedResult(String message, TData data) {
        this.setResult(FAILED, message, data);
    }

    public void setFailedResult(String message, TData data, Object extendData) {
        this.setResult(FAILED, message, data, extendData);
    }

    public void setErrorResult(String message) {
        this.setResult(ERROR, message);
    }

    public void setErrorResult(String message, TData data) {
        this.setResult(ERROR, message, data);
    }

    public void setErrorResult(String message, TData data, Object extendData) {
        this.setResult(ERROR, message, data, extendData);
    }

    public static RestResult createSuccessResult(String message) {
        RestResult restResult = new RestResult();
        restResult.setSuccessResult(message);
        return restResult;
    }

    public static RestResult createSuccessResult(String message, Object data) {
        RestResult restResult = new RestResult();
        restResult.setSuccessResult(message, data);
        return restResult;
    }

    public static RestResult createSuccessResult(String message, Object data, Object extendData) {
        RestResult restResult = new RestResult();
        restResult.setSuccessResult(message, data, extendData);
        return restResult;
    }

    public static RestResult createFailedResult(String message) {
        RestResult restResult = new RestResult();
        restResult.setFailedResult(message);
        return restResult;
    }

    public static RestResult createFailedResult(String message, Object data) {
        RestResult restResult = new RestResult();
        restResult.setFailedResult(message, data);
        return restResult;
    }

    public static RestResult createFailedResult(String message, Object data, Object extendData) {
        RestResult restResult = new RestResult();
        restResult.setFailedResult(message, data, extendData);
        return restResult;
    }

    public static RestResult createErrorResult(String message) {
        RestResult restResult = new RestResult();
        restResult.setErrorResult(message);
        return restResult;
    }

    public static RestResult createErrorResult(String message, Object data) {
        RestResult restResult = new RestResult();
        restResult.setErrorResult(message, data);
        return restResult;
    }

    public static RestResult createErrorResult(String message, Object data, Object extendData) {
        RestResult restResult = new RestResult();
        restResult.setErrorResult(message, data, extendData);
        return restResult;
    }

    public static RestResult createExceptionResult(String message, Exception ex) {
        RestResult restResult = new RestResult();
        restResult.setErrorResult(message, null);
        return restResult;
    }

    public static RestResult createExceptionResult(String message, Object data, Exception ex) {
        RestResult restResult = new RestResult();
        restResult.setErrorResult(message, data);
        return restResult;
    }
}
