package com.example.JVMlearn.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhuang on 2018/8/18.
 */
public class DaoMapperTest {

    public static void main(String[] args) {
        DaoMapper s = (DaoMapper) Proxy.newProxyInstance(DaoMapper.class.getClassLoader(), new Class<?>[]{DaoMapper.class}, new MyInvocationHandler());
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        List<String> result = s.listNameByIds(ids);
        result.forEach(System.out::println);
    }

    static class MyInvocationHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            List<String> result = new ArrayList<>();
            List<Integer> firstParam = (List<Integer> ) args[0];
            for (Object o : firstParam) {
                result.add("name:" + o);
            }
            return result;
        }
    }

}
