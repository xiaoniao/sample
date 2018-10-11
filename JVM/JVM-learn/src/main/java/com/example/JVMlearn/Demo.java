package com.example.JVMlearn;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liuzhuang on 2018/9/17.
 */
public class Demo {

    static class Counter {

        private static AtomicInteger count = new AtomicInteger(0);

        static void increment() {
            count.incrementAndGet();
        }

        static int value() {
            return count.get();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            Counter.increment();
            System.out.println("counter: " + Counter.value());
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
