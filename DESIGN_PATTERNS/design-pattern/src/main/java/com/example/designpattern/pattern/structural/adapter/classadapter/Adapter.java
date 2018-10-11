package com.example.designpattern.pattern.structural.adapter.classadapter;

/**
 * Created by geely
 */
public class Adapter extends ConcreteTarget implements Target {

    @Override
    public void request() {
        //...
        System.out.println("适配器 pre");

        super.request();
        //...
        System.out.println("适配器 after");
    }
}
