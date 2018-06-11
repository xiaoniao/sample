package com.example.springaopandaspectjlearn.cglib.sample1;

import com.example.springaopandaspectjlearn.cglib.sample1.callback.TargetObject;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InterfaceMaker;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by liuzhuang on 6/11/18.
 */
public class TestInterfaceMaker {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        InterfaceMaker interfaceMaker = new InterfaceMaker();

        interfaceMaker.add(TargetObject.class);

        Class<TargetObject> targetInterface = interfaceMaker.create();

        Method[] methods = targetInterface.getMethods();


        Object object = Enhancer.create(Object.class, new Class[]{targetInterface}, new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                if (method.getName().equals("method1")) {
                    return "catch you";
                }
                if (method.getName().equals("method2")) {
                    return 1;
                }
                if (method.getName().equals("method3")) {
                    return 2;
                }
                return null;
            }
        });

        Method targetMethod3 = object.getClass().getMethod("method3", new Class[]{int.class});
        System.out.println(targetMethod3.invoke(object, new Object[]{1000}));


        Method targetMethod1 = object.getClass().getMethod("method1", new Class[]{String.class});
        System.out.println(targetMethod1.invoke(object, new Object[]{"blabla"}));
    }
}
