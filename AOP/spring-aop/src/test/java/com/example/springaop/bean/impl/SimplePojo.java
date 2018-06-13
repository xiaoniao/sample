package com.example.springaop.bean.impl;

import com.example.springaop.bean.Pojo;
import org.springframework.aop.framework.AopContext;

/**
 * Created by liuzz on 2018/06/13
 */
public class SimplePojo implements Pojo {

    @Override
    public void foo() {
         // this.bar();
        ((Pojo) AopContext.currentProxy()).bar();
    }

    @Override
    public void bar() {
        System.out.println("this is bar");
    }
}
