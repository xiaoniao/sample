package com.liuzhuang.queue.deplayqueue;

import java.util.concurrent.BlockingQueue;

/**
 * Created by liuzhuang on 2018/9/21.
 */
public class DelayQueueProducer {

    private BlockingQueue<DelayObject> queue;
    private Integer delayOfEachProducedMessageMilliseconds;

    public DelayQueueProducer(BlockingQueue<DelayObject> queue, Integer delayOfEachProducedMessageMilliseconds) {
        this.queue = queue;
        this.delayOfEachProducedMessageMilliseconds = delayOfEachProducedMessageMilliseconds;
    }

    public void send(String data) {
        DelayObject object = new DelayObject(data, delayOfEachProducedMessageMilliseconds);
        try {
            queue.put(object);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}