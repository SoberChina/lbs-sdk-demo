package com.github.sober.tencent.lbs.sdk.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author liweigao
 * @date 2021/1/25 下午2:54
 */
public class BaseResult<T> {

    private int status;

    private String message;

    private String requestId;

    private T result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getRequestId() {
        return requestId;
    }

    @JSONField(name = "request_id")
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
