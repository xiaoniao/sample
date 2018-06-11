package com.example.springaopandaspectjlearn.cglib.sample1.callback;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by liuzz on 2018/06/11
 */
public class TargetInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object result = methodProxy.invokeSuper(obj, objects);
        System.out.println("调用后 - " + result);
        return result;
    }
}
