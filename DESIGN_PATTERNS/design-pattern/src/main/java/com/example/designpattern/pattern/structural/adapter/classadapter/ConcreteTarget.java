package com.example.designpattern.pattern.structural.adapter.classadapter;

/**
 * Created by geely
 */
public class ConcreteTarget implements Target {

    @Override
    public void request() {
        System.out.println("concreteTarget 目标方法");
    }
}