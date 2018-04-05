package com.example.rpcLearn.rpc2.response;

import java.io.Serializable;

/**
 * Created by liuzz on 2018/03/07
 */
public class RpcResponse implements Serializable {

    // 请求的Id
    private String requestId;

    // 异常
    private Throwable error;

    // 响应
    private Object result;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
