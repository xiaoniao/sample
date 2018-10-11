package com.example.designpattern.pattern.structural.decorator.v2;

/**
 * Created by geely
 * <p>
 * 装饰者 给煎饼加鸡蛋
 */
public class EggDecorator extends AbstractDecorator {

    public EggDecorator(AbstractBatterCake abstractBattercake) {
        super(abstractBattercake);
    }

    @Override
    protected String getDesc() {
        return super.getDesc() + " 加一个鸡蛋";
    }

    @Override
    protected int cost() {
        return super.cost() + 1;
    }

    @Override
    protected void doSomething() {

    }
}
