package io.netty.example.definitive.thread;

/**
 * Created by liuzz on 2018/06/27
 *
 *
 * 1.Reactor单线程模型
 * 2.Reactor多线程模型
 * 3.主从Reactor多线程模型（百万并发）默认就是这个
 *
 *
 * 无锁化设计，IO线程内部进行串行操作，依次执行handler
 *
 */
public class TestEventLoopAndGroup {

    public static void main(String[] args) {



    }
}
