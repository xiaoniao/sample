package com.test.netty.nio;

/**
 * Created by liuzhuang on 2018/3/1.
 */
public class TimeServer {

    public static void main(String[] args) {
        new Thread(new MultiplexerTimeServer(999)).start();

        try {
            "".wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
