package com.example.JVMlearn.bytecode;

/**
 * Created by liuzhuang on 2018/8/11.
 */
public class TestClass {

    private int m;

    public int inc() {
        return m + 1;
    }

    public int inc2() {
        int x;
        try {
            x = 100;
//            throw new RuntimeException();
//            throw new Error();
            return x;
        } catch (Exception e) {
            x = 200;
            return x;
        } finally {
            x = 300;
            // System.out.println("AAA");
        }
    }

    public static void simple() {
        int a = 5;
        int b = 6;
        int c = a + b;
        System.out.println(c);
    }

    public static void main(String[] args) {
        System.out.println(new TestClass().inc2());
    }
}
