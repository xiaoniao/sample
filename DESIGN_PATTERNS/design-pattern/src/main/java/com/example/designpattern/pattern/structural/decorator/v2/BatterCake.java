package com.example.designpattern.pattern.structural.decorator.v2;

/**
 * Created by geely
 *
 * 煎饼实物
 */
public class BatterCake extends AbstractBatterCake {

    @Override
    protected String getDesc() {
        return "煎饼";
    }

    @Override
    protected int cost() {
        return 8;
    }
}
