package com.liuzhuang.thread.threadpool;

public class ThreadPoolTest {

  public static void main(String[] args) throws InterruptedException {
    ThreadPool<Runnable> threadpool = new DefaultThreadPool<Runnable>();
    // threadpool.removeWorker(4);

    for (int i = 0; i < 10; i++) {
      threadpool.execute(new JobRunnable("a" + i));
    }
    
    Thread.sleep(5000);
    for (int i = 10; i < 20; i++) {
      threadpool.execute(new JobRunnable("a" + i));
    }
  }

  static class JobRunnable implements Runnable {

    private String name;

    public JobRunnable(String name) {
      this.name = name;
    }

    public void run() {
      try {
        Thread.sleep(5000);
        System.out.println(name);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
