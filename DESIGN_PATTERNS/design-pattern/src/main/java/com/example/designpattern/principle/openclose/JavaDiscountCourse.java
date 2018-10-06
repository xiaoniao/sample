package com.example.designpattern.principle.openclose;

import java.math.BigDecimal;

/**
 * Created by liuzhuang on 2018/9/23.
 */
public class JavaDiscountCourse extends JavaCourse implements DiscountCourse {

    public JavaDiscountCourse(BigDecimal price) {
        super(price);
    }

    @Override
    public BigDecimal getOriginPrice() {
        return super.getPrice();
    }

    @Override
    public BigDecimal getPrice() {
        return super.getPrice().multiply(BigDecimal.valueOf(0.8));
    }
}
