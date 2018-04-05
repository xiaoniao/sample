package com.example.rpcLearn.rpc.rpc.proxy;

import com.example.rpcLearn.rpc.rpc.Response.RpcResponse;
import com.example.rpcLearn.rpc.rpc.client.RpcClient;
import com.example.rpcLearn.rpc.rpc.discovery.ServiceDiscovery;
import com.example.rpcLearn.rpc.rpc.request.RpcRequest;
import com.example.rpcLearn.rpc.util.LogUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 */
public class RpcProxyFactory {

    // 日期
    private static final Logger logger = LogManager.getLogger(RpcProxyFactory.class);

    // 服务地址
    private String serverAddress;

    // 服务发现
    private ServiceDiscovery serviceDiscovery;

    @SuppressWarnings("unchecked")
    public <T> T create(Class<?> interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass}, new InvocationHandler() {

                    public Object invoke(Object proxy, Method method, Object[] args)
                            throws Throwable {
                        LogUtils.info(logger, "创建的代理请求是：", method.getName());
                        RpcRequest request = new RpcRequest(); // 创建并初始化 RPC 请求
                        request.setRequestId(UUID.randomUUID().toString());
                        request.setClassName(method.getDeclaringClass().getName());
                        request.setMethodName(method.getName());
                        request.setParameterTypes(method.getParameterTypes());
                        request.setParameters(args);
                        // 如果服务发现不为空则主动发现服务，否则使用默认服务地址
                        if (serviceDiscovery != null) {
                            serverAddress = serviceDiscovery.discover(); // 发现服务
                        }
                        //服务的地址是host:port
                        String[] array = serverAddress.split(":");
                        String host = array[0];
                        int port = Integer.parseInt(array[1]);

                        RpcClient client = new RpcClient(host, port); // 初始化 RPC 客户端
                        RpcResponse response = client
                                .send(request); // 通过 RPC 客户端发送 RPC 请求并获取 RPC 响应

                        if (response.isError()) {
                            throw response.getError();
                        } else {
                            return response.getResult();
                        }
                    }
                });
    }

    /**
     * Getter method for property <tt>serverAddress</tt>.
     *
     * @return property value of serverAddress
     */
    public String getServerAddress() {
        return serverAddress;
    }

    /**
     * Setter method for property <tt>serverAddress</tt>.
     *
     * @param serverAddress value to be assigned to property serverAddress
     */
    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    /**
     * Getter method for property <tt>serviceDiscovery</tt>.
     *
     * @return property value of serviceDiscovery
     */
    public ServiceDiscovery getServiceDiscovery() {
        return serviceDiscovery;
    }

    /**
     * Setter method for property <tt>serviceDiscovery</tt>.
     *
     * @param serviceDiscovery value to be assigned to property serviceDiscovery
     */
    public void setServiceDiscovery(ServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

}
