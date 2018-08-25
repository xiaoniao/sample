package com.liuzhuang.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 实现Lock接口,实现自定义独占锁,同一时刻只允许一个线程访问锁,访问不到的加入到等待队列中.
 */
public class Mutex implements Lock {

  private static int IDLE = 0;
  private static int USER = 1;
  
  /**
   * *****自定义同步器*****
   * 
   * 同步状态
   * state 0IDLE闲置 1USER占用
   */
  private static class Sync extends AbstractQueuedSynchronizer {
    private static final long serialVersionUID = -3885675931979092870L;
    
    /**
     * 当前线程是否占有锁
     */
    protected boolean isHeldExclusively() {
      return getState() == USER;
    }

    /**
     * 获取同步状态
     * 独占式
     */
    @Override
    protected boolean tryAcquire(int arg) {
      //int a = 1 / 0;**feature1
      if (compareAndSetState(IDLE, USER)) { //compareAndSetState操作是原子性
        //获取成功
        setExclusiveOwnerThread(Thread.currentThread());//设置当前占有的线程
        System.out.println(Thread.currentThread().getName() + "获取锁成功");
        return true;
      }
      System.out.println(Thread.currentThread().getName() + "获取锁失败" + ",当前获得锁的线程是" + getExclusiveOwnerThread().getName());
      //获取失败,获取失败后会被加入同步队列中等待,先进先出FIFO.
      return false;
    }

    /**
     * 释放同步状态
     * 独占式
     */
    @Override
    protected boolean tryRelease(int arg) {
      if (getState() == IDLE) {
        System.out.println(Thread.currentThread().getName() + "释放锁失败");
        throw new IllegalMonitorStateException();//释放失败
      }
      //释放成功
      setExclusiveOwnerThread(null);//设置当前无占有线程
      setState(IDLE);
      System.out.println(Thread.currentThread().getName() + "释放锁成功");
      return true;
    }

    /**
     * 获取同步状态(未使用)
     * 共享式
     */
    @Override
    protected int tryAcquireShared(int arg) {
      return super.tryAcquireShared(arg);
    }

    /**
     * 释放同步状态(未使用)
     * 共享式
     */
    @Override
    protected boolean tryReleaseShared(int arg) {
      return super.tryReleaseShared(arg);
    }

    //获取与当前锁绑定的等待通知组件(什么类型?)
    Condition newCondition() {
      return new ConditionObject();
    }
  }

  private final Sync sync = new Sync();

  /**
   * 获取锁,调用改方法当前线程将会获得锁,当锁获得后,从该方法返回. 
   * 阻塞式?是怎么实现阻塞的, wait?
   */
  @Override
  public void lock() {
    sync.acquire(USER); //tryAcquire() 会去调用重写的tryAcquire
  }

  /**
   * 获取锁,可响应线程中断.
   * 阻塞式
   */
  @Override
  public void lockInterruptibly() throws InterruptedException {
    sync.acquireInterruptibly(USER);//tryAcquire
  }

  /**
   * 尝试获取锁,获取成功返回true,获取失败返回false;
   * 非阻塞
   */
  @Override
  public boolean tryLock() {
    return sync.tryAcquire(USER);//tryAcquire
  }

  /**
   * 超时的获取锁,当前线程在以下3中情况会返回:
   * 1.在超时时间内,获取了锁.
   * 2.在超时时间内,线程响应中断.
   * 3.超时,返回false.
   */
  @Override
  public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
    return sync.tryAcquireNanos(USER, unit.toNanos(time));//tryAcquire
  }

  /**
   * 释放锁
   */
  @Override
  public void unlock() {
    sync.release(USER);//tryRelease
  }

  /**
   * 获取等待通知组件,该组件和当前线程绑定,当前线程只有获得了锁,才能调用该组件的wait方法,而调用后当前线程将释放锁.
   */
  @Override
  public Condition newCondition() {
    return sync.newCondition();
  }
  
  public boolean isHeldExclusively() {
    return sync.isHeldExclusively();
  }

  /**
   * Lock使用的注意事项
   * 1.获取锁,不要写在try中,因为如果在获取锁时放生了异常,异常抛出的同时,也会导致锁无故释放(待写 在自定义锁中抛出异常)
   * 2.再finally中释放锁,保证能最终被释放.
   */
  public static void main(String[] args) {
    Lock lock = new Mutex();
    
//    //lock
//    for (int i = 0; i < 10; i++) {
//      new Thread(() -> {
//        lock.lock();
//        try {
//          //lock.lock();**feature1测试同步器异常导致的没有获得锁,但是会在finally中释放锁
//          Thread.sleep(1000);
//          System.out.println(Thread.currentThread().getName());
//        } catch (InterruptedException e) {
//          System.out.println("catch");
//          e.printStackTrace();
//        } finally {
//          //System.out.println("finally");
//          //Mutex mutex = (Mutex) lock;
//          //System.out.println(mutex.isHeldExclusively());
//          lock.unlock();
//        }
//      }).start();
//    }
//    
//    //lockInterruptibly
//    for (int i = 0; i < 10; i++) {
//      new Thread(() -> {
//        try {
//          lock.lockInterruptibly();
//        } catch (InterruptedException e1) {
//          e1.printStackTrace();
//        }
//        try {
//          Thread.sleep(1000);
//          System.out.println(Thread.currentThread().getName());
//        } catch (InterruptedException e) {
//          e.printStackTrace();
//        } finally {
//          lock.unlock();
//        }
//      }).start();
//    }
//    
//    //tryLock尝试获取,但立即返回,不阻塞
//    for (int i = 0; i < 10; i++) {
//      new Thread(() -> {
//        boolean result = lock.tryLock();
//        if (result) {
//          try {
//            Thread.sleep(1000);
//            System.out.println(Thread.currentThread().getName());
//          } catch (InterruptedException e) {
//            e.printStackTrace();
//          } finally {
//            lock.unlock();
//          }
//        }
//      }).start();
//    }
//    
    //tryLock(long time, TimeUnit unit) 超时尝试获取,但立即返回,非阻塞
    for (int i = 0; i < 10; i++) {
      new Thread(() -> {
        boolean result = false;
        try {
          result = lock.tryLock(2000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e1) {
          e1.printStackTrace();
        }
        if (result) {
          try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName());
          } catch (InterruptedException e) {
            e.printStackTrace();
          } finally {
            lock.unlock();
          }
        } else {
          System.out.println(Thread.currentThread().getName() + "超时获取锁失败");
        }
      }).start();
    }
    
  }
}
