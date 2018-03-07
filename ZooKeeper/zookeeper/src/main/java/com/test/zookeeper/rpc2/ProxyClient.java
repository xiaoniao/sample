package com.test.zookeeper.rpc2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * Java 动态代理
 *
 * Created by liuzz on 2018/03/07
 */
public class ProxyClient {

    /**
     * 引用服务
     *
     * @param <T> 接口泛型
     * @param interfaceClass 接口类型
     * @param host 服务器主机名
     * @param port 服务器端口
     * @return 远程服务
     */
    @SuppressWarnings("unchecked")
    public static <T> T proxy(final Class<T> interfaceClass, final String host, final int port) throws Exception {
        if (interfaceClass == null || !interfaceClass.isInterface()) {
            throw new IllegalArgumentException("必须指定服务接口");
        }

        if (host == null || host.length() == 0) {
            throw new IllegalArgumentException("必须指定服务器的地址和端口号");
        }

        ClassLoader loader = interfaceClass.getClassLoader();
        Class<?>[] interfaces = new Class<?>[]{interfaceClass};
        return (T) Proxy.newProxyInstance(loader, interfaces, (proxy, method, arguments) -> {
            try (
                Socket socket = new Socket(host, port);
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {

                output.writeUTF(interfaceClass.getName());
                output.writeUTF(method.getName());
                output.writeObject(method.getParameterTypes());
                output.writeObject(arguments);

                Object result = input.readObject();
                if (result instanceof Throwable) {
                    System.out.println("异常");
                    throw (Throwable) result;
                }
                return result;
            }
        });
    }
}