package com.test.springioc.my2;

/**
 * Created by liuzz on 2018/02/05
 */
public class TestApplicationContext {


    public static void main(String[] args) {
        MyBeanFactory ac = new My2ClassPathXmlApplicationContext("/applicationContext.xml");
    }
}
