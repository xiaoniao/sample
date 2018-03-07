package com.test.zookeeper.rpc2;

import com.test.zookeeper.rpc2.anno.SingleRpc;
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
        System.out.println("publishedService");
        server = new ServerSocket(port);

        for (; ; ) {
            try {
                final Socket socket = server.accept();
                System.out.println("socket : " + socket.hashCode());
                executorService.execute(() -> {
                    try {
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

                        System.out.println("Server : A");
                        try {
                            String interfaceName = input.readUTF();
                            String methodName = input.readUTF();
                            Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
                            Object[] arguments = (Object[]) input.readObject();
                            System.out.println("Server interfaceName : " + interfaceName + " methodName :" + methodName);
                            if (parameterTypes != null) {
                                for (Class<?> clz : parameterTypes) {
                                    System.out.println("Server parameterType : " + clz);
                                }
                            }
                            if (arguments != null) {
                                for (Object obj : arguments) {
                                    System.out.println("Server argument : " + obj);
                                }
                            }
                            try {
                                Object service = handlerMap.get(interfaceName);
                                Method method = service.getClass().getMethod(methodName, parameterTypes);
                                Object result = method.invoke(service, arguments);
                                System.out.println("Server result : " + result);
                                output.writeObject(result);
                            } catch (Throwable t) {
                                t.printStackTrace();
                                output.writeObject(t);
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
