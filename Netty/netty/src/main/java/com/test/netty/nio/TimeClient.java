package com.test.netty.nio;

/**
 * Created by liuzhuang on 2018/3/3.
 */
public class TimeClient {
    public static void main(String[] args) {
        new Thread(new TimeClientHandle("", 9000)).start();

        try {
            "".wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
