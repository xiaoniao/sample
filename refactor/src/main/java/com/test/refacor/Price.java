package com.test.refacor;

/**
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

    public double getPrice() {
        double basePrice = quantity * itemPrice;

        int discountLevel;
        if (quantity > 100) {
            discountLevel = 2;
        } else {
            discountLevel = 1;
        }
        double finalPrice = discountedPrice(basePrice, discountLevel);
        return finalPrice;
    }

    private double discountedPrice(double basePrice, int discountLevel) {
        if (discountLevel == 2) {
            return basePrice * 0.1;
        }
        return basePrice * 0.05;
    }

    public static void main(String[] args) {
        Price price = new Price();
        price.quantity = 10;
        price.itemPrice = 5;
        System.out.println(price.getPrice());
    }

}
