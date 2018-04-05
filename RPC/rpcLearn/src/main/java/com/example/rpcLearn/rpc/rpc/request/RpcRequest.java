/**
 * Alipay.com Inc. Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.example.rpcLearn.rpc.rpc.request;

import java.util.Arrays;

/**
 * Rpc 请求的主体
 */
public class RpcRequest {

    // 请求Id
    private String requestId;

    // 远程调用类名称
    private String className;

    //远程调用方法名称
    private String methodName;

    // 参数类型
    private Class<?>[] parameterTypes;

    // 参数值
    private Object[] parameters;

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
     * Getter method for property <tt>className</tt>.
     *
     * @return property value of className
     */
    public String getClassName() {
        return className;
    }

    /**
     * Setter method for property <tt>className</tt>.
     *
     * @param className value to be assigned to property className
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Getter method for property <tt>methodName</tt>.
     *
     * @return property value of methodName
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * Setter method for property <tt>methodName</tt>.
     *
     * @param methodName value to be assigned to property methodName
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * Getter method for property <tt>parameterTypes</tt>.
     *
     * @return property value of parameterTypes
     */
    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    /**
     * Setter method for property <tt>parameterTypes</tt>.
     *
     * @param parameterTypes value to be assigned to property parameterTypes
     */
    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    /**
     * Getter method for property <tt>parameters</tt>.
     *
     * @return property value of parameters
     */
    public Object[] getParameters() {
        return parameters;
    }

    /**
     * Setter method for property <tt>parameters</tt>.
     *
     * @param parameters value to be assigned to property parameters
     */
    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "RpcRequest [requestId=" + requestId + ", className=" + className + ", methodName="
                + methodName + ", parameterTypes=" + Arrays.toString(parameterTypes)
                + ", parameters=" + Arrays.toString(parameters) + "]";
    }

}
