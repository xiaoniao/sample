package com.liuzhuang.thread;

public class ThreadSimple {

	static class MyThread extends Thread {

		public MyThread() {
			super();
		}

		public MyThread(Runnable target) {
			super(target);
		}

		@Override
		public void run() {
			System.out.println(getName());
//			System.out.println("---线程前置条件");
			super.run();
//			System.out.println("线程后置条件---");
		}
	}

	public static void main(String[] args) {
		// 可线程 run 什么也不执行
		Thread thread = new Thread();
		thread.start();

		// 执行重写的run
		MyThread myThread = new MyThread();
		myThread.start();

		// 执行重写的run 和 Runnable的run（这种做法可以对线程做统一的处理 前置或者后置）
		MyThread myRunnableThread = new MyThread(new Runnable() {
			public void run() {
				System.out.println("B");
			}
		});
		myRunnableThread.start();
		
		// 调用start后 run方法被jvm执行 ， 线程执行的方式是抢占机制 可以看到执行不是按照顺序执行
		for (int i = 0; i < 1000; i++) {
			MyThread t = new MyThread();
			t.start();
		}
	}
	
	/**
	 * 进程 共享内存 独享内存线程栈
	 * 
	 * start() 后被 JVM 执行run()方法   start()可以协调系统的资源
	 * 
	 * 
	 * 通过Thread类来启动Runnable实现的多线程  -> 所以说 Runnable不是Thread的所属关系，而且Thread 也实现了 Runnable的接口
	 * 
	 * 原子性，可被拆分的最小执行片段
	 * 
	 * Runnable是可以共享数据的，多个Thread可以同时加载一个Runnable，当各自Thread获得CPU时间片的时候开始运行Runnable，Runnable里面的资源是被共享的，所以使用Runnable更加的灵活。
	 */
}
