package com.example.elasticsearchexample;

/**
 * Created by liuzhuang on 2018/7/27.
 */
public class Utils {

    public static void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
