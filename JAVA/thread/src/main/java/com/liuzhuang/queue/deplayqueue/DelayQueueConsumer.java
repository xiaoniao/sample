package com.liuzhuang.queue.deplayqueue;

import java.util.concurrent.BlockingQueue;

/**
 * Created by liuzhuang on 2018/9/21.
 */
public class DelayQueueConsumer implements Runnable {

    private BlockingQueue<DelayObject> queue;

    public DelayQueueConsumer(BlockingQueue<DelayObject> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (; ; ) {
            try {
                DelayObject object = queue.take();
                System.out.println(object.getData() + " : " + System.currentTimeMillis());

                // 判断数据是否被点评，如果没有，则从池中删除记录

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}