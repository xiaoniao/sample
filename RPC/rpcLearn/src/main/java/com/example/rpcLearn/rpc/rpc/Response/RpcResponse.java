/**
 * Alipay.com Inc. Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.example.rpcLearn.rpc.rpc.Response;

/**
 * Rpc 响应的主体
 */
public class RpcResponse {

    // 请求的Id
    private String requestId;

    // 异常
    private Throwable error;

    // 响应
    private Object result;

    /**
     * Getter method for property <tt>requestId</tt>.
     *
     * @return property value of requestId
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Setter method for property <tt>requestId</tt>.
     *
     * @param requestId value to be assigned to property requestId
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    /**
     * Getter method for property <tt>error</tt>.
     *
     * @return property value of error
     */
    public Throwable getError() {
        return error;
    }

    /**
     * Setter method for property <tt>error</tt>.
     *
     * @param error value to be assigned to property error
     */
    public void setError(Throwable error) {
        this.error = error;
    }

    /**
     * Getter method for property <tt>result</tt>.
     *
     * @return property value of result
     */
    public Object getResult() {
        return result;
    }

    /**
     * Setter method for property <tt>result</tt>.
     *
     * @param result value to be assigned to property result
     */
    public void setResult(Object result) {
        this.result = result;
    }

    /**
     *如果有异常则表示失败
     * @return
     */
    public boolean isError() {
        return error != null;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "RpcResponse [requestId=" + requestId + ", error=" + error + ", result=" + result
                + "]";
    }

}
