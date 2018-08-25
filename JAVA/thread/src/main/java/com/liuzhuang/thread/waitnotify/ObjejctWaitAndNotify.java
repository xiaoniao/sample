package com.liuzhuang.thread.waitnotify;

/**
 * Object 的notify和wait使用 
 * 
 * 它必须和 synchronized 一块才能使用
 */
public class ObjejctWaitAndNotify {

  static Object lock = new Object();
  static Object lock2 = new Object();

  /**
   * 错误使用方式
   */
  public static void wrongUser() {
    try {
      lock.wait();
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      lock.notify();
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      new Thread(() -> {
        synchronized (lock) {
          try {
            lock.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        System.out.println("Thread die");
      }).start();
      new Thread(() -> {
        synchronized (lock2) {
          lock.notify(); // 拿前朝的剑,斩本朝的官
        }
      }).start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * wait或notify前必须先获得锁
   */
  public static void user() {
    System.out.println("use start");
    new Thread(() -> {
      synchronized (lock) {
        try {
          System.out.println("wait");
          lock.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      System.out.println("Thread die");
    }).start();
    new Thread(() -> {
      synchronized (lock) {
        System.out.println("notify");
        lock.notify();
      }
    }).start();
    System.out.println("use end");
  }

  /**
   * notify一串等待的线程
   */
  public static void notifyAlls() {
    for (int i = 0; i < 10; i++) {
      final int index = i;
      new Thread(() -> {
        synchronized (lock) {
          try {
            System.out.println("wait" + index);
            lock.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        System.out.println("Thread1 die" + index);
      }).start();
    }
    new Thread(() -> {
      synchronized (lock) {
        lock.notifyAll();
        System.out.println("开始唤醒wait串了");
      }
    }).start();
  }

  /**
   * 没人唤醒的例子
   * 没人唤醒多悲惨,还浪费资源
   */
  public static void alwaysWait() {
    new Thread(() -> {
      synchronized (lock) {
        try {
          lock.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }
  
  /**
   * 加上超时的wait,不至于没人管
   */
  public static void waitTimeout() {
    new Thread(() -> {
      synchronized (lock) {
        try {
          lock.wait(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("3秒后返回 die");
      }
    }).start();
  }

  public static void main(String[] args) {
    // wrongUser();
    // user();
    // notifyAlls();
    // alwaysWait();
    // waitTimeout();
  }
}
