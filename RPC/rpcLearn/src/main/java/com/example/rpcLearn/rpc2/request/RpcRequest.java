package com.example.rpcLearn.rpc2.request;

import java.io.Serializable;

/**
 * Created by liuzz on 2018/03/07
 */
public class RpcRequest implements Serializable {

    // 请求Id
    private String requestId;

    // 远程调用接口名称
    private String interfaceName;

    //远程调用方法名称
    private String methodName;

    // 参数类型
    private Class<?>[] parameterTypes;

    // 参数值
    private Object[] parameters;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
