package com.example.designpattern.principle.openclose;

import java.math.BigDecimal;

/**
 * Created by liuzhuang on 2018/9/23.
 */
public class JavaCourse implements Course {

    private BigDecimal price;

    public JavaCourse(BigDecimal price) {
        this.price = price;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }
}
