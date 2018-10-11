package com.example.designpattern.pattern.structural.decorator.v2;

/**
 * Created by geely
 *
 * 抽象装饰者
 */
public abstract class AbstractDecorator extends AbstractBatterCake {

    private AbstractBatterCake abstractBattercake;

    /**
     * abstractBattercake 可以是实物也可以是装饰类
     */
    public AbstractDecorator(AbstractBatterCake abstractBattercake) {
        this.abstractBattercake = abstractBattercake;
    }

    @Override
    protected String getDesc() {
        return abstractBattercake.getDesc();
    }

    @Override
    protected int cost() {
        return abstractBattercake.cost();
    }

    protected abstract void doSomething();
}
