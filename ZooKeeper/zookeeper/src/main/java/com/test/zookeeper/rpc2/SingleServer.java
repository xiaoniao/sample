package com.test.zookeeper.rpc2;

import com.test.zookeeper.rpc2.anno.SingleRpc;
import com.test.zookeeper.rpc2.request.RpcRequest;
import com.test.zookeeper.rpc2.response.RpcResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Component;

/**
 * 单机服务
 *
 * Created by liuzz on 2018/03/06
 */
@Component
public class SingleServer implements InitializingBean, Lifecycle, ApplicationContextAware {

    private int port = 12000;

    private ServerSocket server;

    public Map<String, Object> handlerMap = new ConcurrentHashMap<>();

    private Executor executorService = Executors.newFixedThreadPool(10);

    private void publishedService() throws Exception {
        server = new ServerSocket(port);

        for (; ; ) {
            try {
                final Socket socket = server.accept();
                executorService.execute(() -> {
                    try {
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

                        try {
                            RpcRequest rpcRequest = (RpcRequest) input.readObject();
                            try {
                                RpcResponse rpcResponse = doHandle(rpcRequest);
                                output.writeObject(rpcResponse);
                            } finally {
                                input.close();
                            }
                        } finally {
                            socket.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private RpcResponse doHandle(RpcRequest request) {
        RpcResponse response = new RpcResponse();
        response.setRequestId(request.getRequestId());
        try {
            Object service = handlerMap.get(request.getInterfaceName());
            Method method = service.getClass().getMethod(request.getMethodName(), request.getParameterTypes());
            response.setResult(method.invoke(service, request.getParameters()));
        } catch (Exception e) {
            response.setError(e);
        }
        return response;
    }

    /***************** InitializingBean *******************/

    @Override
    public void afterPropertiesSet() throws Exception {
        publishedService();
    }

    /***************** ApplicationContextAware *******************/

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(SingleRpc.class);
        if (beanMap != null) {
            for (Object bean : beanMap.values()) {
                String interfaceName = bean.getClass().getAnnotation(SingleRpc.class).interf().getName();
                System.out.println("interfaceName:" + interfaceName);
                handlerMap.put(interfaceName, bean);
            }
        }
    }

    /***************** Lifecycle *******************/

    @Override
    public void start() {

    }

    @Override
    public void stop() {
        if (server != null) {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
