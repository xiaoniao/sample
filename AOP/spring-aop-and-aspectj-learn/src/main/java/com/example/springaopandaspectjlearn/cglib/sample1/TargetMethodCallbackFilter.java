package com.example.springaopandaspectjlearn.cglib.sample1;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * Created by liuzz on 2018/06/11
 */
public class TargetMethodCallbackFilter implements CallbackFilter {

    @Override
    public int accept(Method method) {
        if (method.getName().equals("method1")) {
            System.out.println("filter method1 ==0");
            return 0;
        }
        if (method.getName().equals("method2")) {
            System.out.println("filter method2 ==1");
            return 1;
        }
        if (method.getName().equals("method3")) {
            System.out.println("filter method3 ==2");
            return 2;
        }
        return 0;
    }
}
