package com.liuzhuang.threadpool;

import java.util.Date;

/**
 * Created by liuzhuang on 2018/9/11.
 */
public class Test extends Date {

    public static void main(String[] args) {
        new Test().test();
    }

    public void test() {
        System.out.println(getClass().getName());
        System.out.println(super.getClass().getName());
        System.out.println(Test.class.getSuperclass().getName());
        System.out.println(super.toString());
    }
}
