package com.liuzhuang.queue.deplayqueue;

import java.util.concurrent.*;

/**
 * Created by liuzhuang on 2018/9/21.
 */
public class DelayTestCase {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<DelayObject> queue = new DelayQueue<>();
        int delayOfEachProducedMessageMilliseconds = 5000;
        DelayQueueConsumer consumer = new DelayQueueConsumer(queue);
        DelayQueueProducer producer = new DelayQueueProducer(queue, delayOfEachProducedMessageMilliseconds);
        new Thread(consumer).start();

        int i = 1;
        while(true) {
            producer.send(String.valueOf(i++) + "" + System.currentTimeMillis());
            Thread.sleep(200);
        }
    }
}
