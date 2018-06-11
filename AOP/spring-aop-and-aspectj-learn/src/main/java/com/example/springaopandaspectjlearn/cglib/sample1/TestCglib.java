package com.example.springaopandaspectjlearn.cglib.sample1;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

/**
 * Created by liuzz on 2018/06/11
 */
public class TestCglib {

    public static void main(String[] args) {
        filter();
    }

    private static void filter() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetObject.class);

        Callback noOpCallback = NoOp.INSTANCE;
        Callback callback = new TargetInterceptor();
        Callback fixedValue = new TargetResultFixed();
        Callback[] callbacks = new Callback[]{noOpCallback, callback, fixedValue};
        enhancer.setCallbacks(callbacks);

        CallbackFilter callbackFilter = new TargetMethodCallbackFilter();
        enhancer.setCallbackFilter(callbackFilter);

        TargetObject targetObject = (TargetObject) enhancer.create();
        System.out.println("result : " + targetObject.method1("hello"));
        System.out.println("result : " + targetObject.method2(1));
        System.out.println("result : " + targetObject.method3(2));
    }

    private static void basicUse() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetObject.class);
        enhancer.setCallback(new TargetInterceptor());

        // 每次都是新生成一个实例
        // TargetObject$$EnhancerByCGLIB$$66b88077@652
        TargetObject targetObject = (TargetObject) enhancer.create();
        targetObject.method1("hello");
        targetObject.method2(1);
        targetObject.method3(2);

        // TargetObject$$EnhancerByCGLIB$$66b88077@653
        TargetObject targetObject2 = (TargetObject) enhancer.create();
        targetObject2.method1("world");
        targetObject2.method2(3);
        targetObject2.method3(4);

        System.out.println(targetObject == targetObject2);
    }
}