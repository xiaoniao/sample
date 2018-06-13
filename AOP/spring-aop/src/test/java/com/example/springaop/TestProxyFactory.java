package com.example.springaop;

import com.example.springaop.bean.Pojo;
import com.example.springaop.bean.impl.SimplePojo;
import org.junit.Test;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

/**
 * 纯JAVA 实现 Spring AOP
 *
 * Created by liuzz on 2018/06/13
 */
public class TestProxyFactory {


    @Test
    public void testProxy() {
        ProxyFactory proxyFactory = new ProxyFactory(new SimplePojo());
        proxyFactory.addAdvice((MethodBeforeAdvice) (method, objects, o) -> {
            System.out.println("*******");
        });
        proxyFactory.addAdvice((AfterReturningAdvice) (o, method, objects, o1) -> {
            System.out.println("-------");
        });
        proxyFactory.setExposeProxy(true);

        Pojo pojo = (Pojo) proxyFactory.getProxy();
        pojo.foo();
    }
}
