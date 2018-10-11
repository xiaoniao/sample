package com.example.designpattern.pattern.structural.adapter.classadapter;

/**
 * Created by geely
 *
 * 适配器
 *
 *      适配器 实现适配接口，然后调用实际产品
 */
public class Test {

    public static void main(String[] args) {
        Target target = new ConcreteTarget();
        target.request();

        System.out.println("====");

        Target adapterTarget = new Adapter();
        adapterTarget.request();
    }
}
