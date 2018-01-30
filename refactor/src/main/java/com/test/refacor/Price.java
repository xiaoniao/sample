package com.test.refacor;

/**
 * 重构去除参数
 *
 * Created by liuzz on 2018/01/30
 */
public class Price {

    /**
     * 数量
     */
    private int quantity;

    /**
     * 单价
     */
    private double itemPrice;

    /**
     * 获得打折等级，计算价格
     *
     * @return
     */
    public double getPrice() {
        if (getDiscountLevel() == 2) {
            return getBasePrice() * 0.1;
        } else {
            return getBasePrice() * 0.05;
        }
    }

    private double getBasePrice() {
        return quantity * itemPrice;
    }

    private int getDiscountLevel() {
        if (quantity > 100) {
            return 2;
        }
        return 1;
    }

    public static void main(String[] args) {
        Price price = new Price();
        price.quantity = 10;
        price.itemPrice = 5;
        System.out.println(price.getPrice());
    }

}
