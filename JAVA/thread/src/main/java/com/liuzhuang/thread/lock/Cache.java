package com.liuzhuang.thread.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁示例,用lock实现map的线程安全
 * 
 * 读锁
 * 写锁, 一个线程获取写锁后,其他线程的写锁和读锁均会被阻塞;
 * 
 * 读写锁,保证了写锁对所有读取操作的可见性.
 */
public class Cache {

  static Map<String, Object> map = new HashMap<>();
  static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
  static Lock readLock = readWriteLock.readLock();
  static Lock writeLock = readWriteLock.writeLock();

  public static final Object get(String key) {
    readLock.lock();
    try {
      return map.get(key);
    } finally {
      readLock.unlock();
    }
  }

  public static final Object put(String key, Object object) {
    writeLock.lock();
    try {
      return map.put(key, object);
    } finally {
      writeLock.unlock();
    }
  }

  public static final void clear() {
    writeLock.lock();
    try {
      map.clear();
    } finally {
      writeLock.unlock();
    }
  }

}
