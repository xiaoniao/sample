package com.liuzhuang.thread.lock;

import java.util.IllformedLocaleException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class TwinsLock implements Lock {

  private Sync sync = new Sync(2);

  @Override
  public void lock() {
    sync.acquireShared(1);
  }

  @Override
  public void unlock() {
    sync.releaseShared(1);
  }
  
  @Override
  public void lockInterruptibly() throws InterruptedException {
    
  }

  @Override
  public boolean tryLock() {
    return false;
  }

  @Override
  public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
    return false;
  }

  @Override
  public Condition newCondition() {
    return sync.newCondition();
  }

  /**
   * 同一时刻,最多允许两个线程同时访问,超过两个线程访问将被阻塞
   */
  private static class Sync extends AbstractQueuedSynchronizer {
    private static final long serialVersionUID = 6536702916588880193L;

    public Sync(int count) {
      if (count < 0) {
        throw new IllformedLocaleException("count must large than zero.");
      }
      System.out.println("默认状态值:" + getState());
      setState(count);// 设置新的默认同步状态值
    }

    /**
     * 共享式获取资源 >=0才可以获取,否则阻塞.
     */
    @Override
    protected int tryAcquireShared(int reduceCount) {
      for (;;) {
        int current = getState();
        int newCount = current - reduceCount;
        if (newCount < 0 || compareAndSetState(current, newCount)) {
          return newCount;
        }
      }
    }

    /**
     * 释放资源
     */
    @Override
    protected boolean tryReleaseShared(int returnCount) {
      for (;;) {
        int currentCount = getState();
        int newCount = currentCount + returnCount;
        if (compareAndSetState(currentCount, newCount)) {
          return true;
        }
      }
    }
    
    public Condition newCondition() {
      return new ConditionObject();
    }
  }

}
