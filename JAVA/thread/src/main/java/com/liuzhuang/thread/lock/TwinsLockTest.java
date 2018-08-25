package com.liuzhuang.thread.lock;

import java.util.concurrent.locks.Lock;

/**
 * TwinsLock 同一时间最多有两个线程进入
 *
 */
public class TwinsLockTest {

  public static void main(String[] args) {
    Lock lock = new TwinsLock();
    for (int i = 0; i < 10; i++) {
      new Thread(() -> {
        lock.lock();
        try {
          System.out.println(Thread.currentThread().getName());
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
          lock.unlock();
        }
      }).start();
    }
  }
}
