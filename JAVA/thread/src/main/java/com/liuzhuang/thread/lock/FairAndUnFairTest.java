package com.liuzhuang.thread.lock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 可重入锁
 * 指定的是如果一个线程已经获得了锁,那么在进入线程还是已经获得了锁. 第一次lock是根据同步状态 第二次是根据当前线程和锁线程是否相等.
 * 
 * Lock公平锁,非公平锁
 * 1.非公平锁效率会高一些,因为公平锁会去判断当前获得锁的线程是不是在队列头,而非公平锁不用判断.
 * 2.非公平锁更贪婪,比如 thread { while(true) { lock.lock() lock.unLock() }},在线程循环中释放锁之后,很容易再次获得锁,因为它当前被cpu执行的概率最大.
 *   而公平锁要判断当前线程是不是第一个,所以会造成线程上下文切换频繁.
 */
public class FairAndUnFairTest {

  // 公平锁
  private static Lock fairLock = new ReentrantLock2(true);
  // 非公平锁
  private static Lock unFiarLock = new ReentrantLock2(false);

  public static void main(String[] args) throws InterruptedException {
    System.out.println("公平锁");
    test(fairLock);

    Thread.sleep(5000);
    System.out.println("非公平锁");
    test(unFiarLock);
  }

  private static void test(Lock lock) {
    for (int i = 0; i < 10; i++) {
      new Thread(() -> {
        for (int j = 0; j < 100; j++) {
          lock.lock();
          try {
            ReentrantLock2 reentrantLock2 = (ReentrantLock2) lock;
            // 打印线程名字
            System.out.println("lock by" + Thread.currentThread().getName() + ",waiting is "
                + logThreadName(reentrantLock2.getQueuedThreads()));
          } finally {
            lock.unlock();
          }
        }
      }, String.valueOf(i)).start();
    }
  }

  private static String logThreadName(Collection<Thread> threads) {
    StringBuffer stringBuffer = new StringBuffer("[");
    threads.forEach((Thread thread) -> {
      stringBuffer.append(thread.getName()).append(",");
    });
    String aString = stringBuffer.toString() + "]";
    return aString.replaceAll(",]", "]");
  }

  /**
   * 打印线程
   */
  static class ReentrantLock2 extends ReentrantLock {
    private static final long serialVersionUID = 7252846765486491686L;

    public ReentrantLock2(boolean fair) {
      super(fair);
    }

    @Override
    protected Collection<Thread> getQueuedThreads() {
      List<Thread> list = new ArrayList<>(super.getQueuedThreads());
      Collections.reverse(list);
      return list;
    }
  }
}
