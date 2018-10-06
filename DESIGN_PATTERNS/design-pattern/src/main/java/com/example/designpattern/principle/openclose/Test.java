package com.example.designpattern.principle.openclose;

import java.math.BigDecimal;

/**
 *
 * 开闭原则，对修改关闭，对扩展开放；
 *
 * 不能修改之前的代码，应该通过新增类来实现新的扩展功能
 *
 *
 * 总结：是通过新增了一个类来实现打折功能
 * Created by liuzhuang on 2018/9/23.
 */
public class Test {

    public static void main(String[] args) {
        Course course = new JavaDiscountCourse(BigDecimal.TEN);
        JavaDiscountCourse javaDiscountCourse = (JavaDiscountCourse) course;
        System.out.println(javaDiscountCourse.getPrice() + " " + javaDiscountCourse.getOriginPrice());

        DiscountCourse discountCourse = (DiscountCourse) course;
        System.out.println(course.getPrice() + " " + discountCourse.getOriginPrice());
    }
}
