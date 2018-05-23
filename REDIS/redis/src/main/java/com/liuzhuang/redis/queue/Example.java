package com.liuzhuang.redis.queue;

public class Example {

  public static void main(String[] args) throws InterruptedException {
    FifoQueueThread queue = new FifoQueueThread();
    queue.start();

    sleep(1000);
    queue.addToQueue("a" + System.currentTimeMillis());
    sleep(2000);
    queue.addToQueue("b" + System.currentTimeMillis());
  }

  private static void sleep(long time) {
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
