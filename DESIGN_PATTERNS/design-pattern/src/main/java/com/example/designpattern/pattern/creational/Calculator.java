package com.example.designpattern.pattern.creational;

import java.math.BigDecimal;

/**
 *
 * 输入两个数和运算符号，得到结果
 *
 * Created by liuzhuang on 2018/9/23.
 */
public class Calculator {


    public static void main(String[] args) {
        calculator(3.0, 5.0, "+");
        calculator(3.0, 5.0, "-");
        calculator(3.0, 5.0, "*");
        calculator(3.0, 5.0, "/");
    }

    public static void calculator(double number1, double number2, String operate) {
        BigDecimal bigDecimal1 = BigDecimal.valueOf(number1);
        BigDecimal bigDecimal2 = BigDecimal.valueOf(number2);

        BigDecimal result;
        if (operate.equals("+")) {
            result = bigDecimal1.add(bigDecimal2);
        } else if (operate.equals("-")) {
            result = bigDecimal1.subtract(bigDecimal2);
        } else if (operate.equals("*")) {
            result = bigDecimal1.multiply(bigDecimal2);
        } else if (operate.equals("/")) {
            result = bigDecimal1.divide(bigDecimal2);
        } else {
            throw new RuntimeException("operate");
        }
        System.out.println(result);
    }

}
