package com.example.designpattern.pattern.structural.decorator.v2;

/**
 * Created by geely
 *
 *
 * 把实物抽象成产品，
 *
 * 把装饰类抽象成产品，和产品同等等级，调用接口
 *
 */
public class Test {
    public static void main(String[] args) {
        AbstractBatterCake battercake = new BatterCake();
        System.out.println(battercake.getDesc() + " 销售价格:" + battercake.cost());

        battercake = new EggDecorator(battercake);
        System.out.println(battercake.getDesc() + " 销售价格:" + battercake.cost());

        battercake = new EggDecorator(battercake);
        System.out.println(battercake.getDesc() + " 销售价格:" + battercake.cost());

        battercake = new SausageDecorator(battercake);
        System.out.println(battercake.getDesc() + " 销售价格:" + battercake.cost());
    }
}
