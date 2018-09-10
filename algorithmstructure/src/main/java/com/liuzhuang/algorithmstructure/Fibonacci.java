package com.liuzhuang.algorithmstructure;

import java.math.BigInteger;

/**
 * Created by liuzhuang on 2018/9/10.
 */
public class Fibonacci {

    public static void main(String[] args) {

        for (int i = 1; i < 1000; i++) {
            // System.out.println("i:" + i + " : " + getFibonacciValue(i));
            System.out.println("i:" + i + " : " + getFibonacciValue2(i));
        }
    }

    /**
     * 效率最低，重复计算
     * @param index
     * @return
     */
    private static int getFibonacciValue(int index) {
        if (index == 1 || index == 2) {
            return 1;
        }
        if (index == 3) {
            return 2;
        }
        return getFibonacciValue(index - 1) + getFibonacciValue(index - 2);
    }

    /**
     *
     *
     * ①  ②  ③   ④   ⑤
     *
     * 1   1   2   3    5
     *
     *     a   b   c
     *
     *         a   b   c
     *
     */
    private static BigInteger getFibonacciValue2(int index) {
        if (index == 1 || index == 2) {
            return BigInteger.valueOf(1L);
        }
        if (index == 3) {
            return BigInteger.valueOf(2L);
        }
        BigInteger a = BigInteger.valueOf(1L);
        BigInteger b = BigInteger.valueOf(2L);
        BigInteger c = BigInteger.valueOf(0L);
        for (int count = 3; count < index; count++) {
            c = a.add(b);
            a = b;
            b = c;
        }
        return c;
     }
}
