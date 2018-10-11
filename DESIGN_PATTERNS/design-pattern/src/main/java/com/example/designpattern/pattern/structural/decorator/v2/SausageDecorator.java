package com.example.designpattern.pattern.structural.decorator.v2;

/**
 * Created by geely
 *
 * 装饰者 给煎饼加香肠
 */
public class SausageDecorator extends AbstractDecorator {

    public SausageDecorator(AbstractBatterCake abstractBattercake) {
        super(abstractBattercake);
    }

    @Override
    protected String getDesc() {
        return super.getDesc() + " 加一根香肠";
    }

    @Override
    protected int cost() {
        return super.cost() + 2;
    }

    @Override
    protected void doSomething() {

    }
}
