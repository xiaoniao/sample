package com.liuzhuang.main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    //deadLock();
//    deadLock2();
    
    String a = "insert into kycom_u_cross_times (user_id, time, status) values (#1#, '#2#', 0);";
    String[] aa = {"30", "32", "33", "34", "35", "36", "37", "38", "40", "44", "47", "74", "75", "78"};
    

    for(String aString : aa) {
      
      Date now = new Date();
      
      for(int i = 0; i < 10; i++) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        System.out.println(a.replace("#1#", aString).replace("#2#", simpleDateFormat.format(now)));
        
        now = new Date(now.getTime() + 24 * 60 * 60 * 1000);
      }
     
    }
    
  }

  /**
   * 死锁的例子<br>
   * 两个线程相互锁住对象,但又要访问另一个线程锁住的对象.
   * 执行命令行可查看线程信息
   * 
   * ===================
   * jps 查看进程ID
   *    9095 Jps
   *    9080 Main
   *    493 NutstoreGUI
   *   
   * ==========================
   * jstack 9080 查看进程中线程情况
   * 
   *  Found one Java-level deadlock:
   *  "Thread-1":
   *    waiting to lock monitor 0x00007fafbb80b4c8 (object 0x000000079561a450, a java.lang.String),
   *    which is held by "Thread-0"
   *  "Thread-0":
   *    waiting to lock monitor 0x00007fafbb80dca8 (object 0x000000079561a480, a java.lang.String),
   *    which is held by "Thread-1"
   */
  public static void deadLock() throws InterruptedException {
    String aString = "a";
    String bString = "b";

    Thread threadA = new Thread(() -> {
      synchronized (aString) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("wait bString");
        synchronized (bString) {
          System.out.println("enter b");
        }
      }
    });
    threadA.start();

    Thread threadB = new Thread(() -> {
      synchronized (bString) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("wait aString");
        synchronized (aString) {
          System.out.println("enter a");
        }
      }
    });
    threadB.start();
  }
  
  /**
   * 模拟一个线程给一个对象加锁了,但没释放锁,导致的死锁.
   */
  public static void deadLock2() throws InterruptedException {
    Object lock = new Object();
    Thread thread = new Thread(()-> {
      synchronized(lock) {
        throw new NullPointerException();
      }
    });
    thread.start();
    
    Thread.sleep(1000);
    Thread thread2 = new Thread(()-> {
      synchronized(lock) {
        System.out.println("enter lock");
      }
    });
    thread2.start();
    
  }
  
  /**
   * 获取某一时刻,所有的线程信息
   */
  public static void printAllThreadTraces() {
    Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
    for (Thread thread : map.keySet()) {
      System.out.println("=======" + thread.getName() + "=======");
      StackTraceElement[] stackTraceElements = map.get(thread);
      for (StackTraceElement element : stackTraceElements) {
        System.out.println(element);
      }
    }
  }

}
