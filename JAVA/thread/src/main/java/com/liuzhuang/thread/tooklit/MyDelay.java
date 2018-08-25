package com.liuzhuang.thread.tooklit;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 自己实现一个延迟加载器
 */
public class MyDelay {

  public static void main(String[] args) {
    delay(2000, new Runnable() {

      @Override
      public void run() {
        System.out.println("hello");
      }
    });
    System.out.println("aaa");
    timeDelay();
  }

  // 简单的实现延迟执行任务
  private static void delay(int time, Runnable runnable) {
    new Thread(() -> {
      Object lock = new Object();
      synchronized (lock) {
        try {
          lock.wait(time);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      runnable.run();
    }).start();
  }

  /**
   * 使用系统提供的类,执行延迟任务
   */
  private static void timeDelay() {
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {

      @Override
      public void run() {
        System.out.println("hello2");
      }
    }, 1000);
  }

}
