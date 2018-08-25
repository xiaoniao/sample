package com.liuzhuang.thread;

/**
 * 查看线程状态
 * NEW 新建
 * RUNNABLE 运行中
 * BLOCKED 阻塞状态
 * WAITING 等待状态
 * TIME_WAITING 超时等待状态
 * TERMINATED 终止状态
 */
public class ThreadState {

	public static void main(String[] args) {
//		new Thread(new New(), "NEW");
//		new Thread(new RunnableState(), "RunnableState").start();;
//		new Thread(new Blocked(), "Blocked1").start();
//		new Thread(new Blocked(), "Blocked2").start(); // waiting to lock <0x0000000797b10178> (a java.lang.Class for com.liuzhuang.thread.ThreadState$Blockeds
//		new Thread(new Waiting(), "Waiting").start();
		new Thread(new TimeWaiting(), "TimeWaiting").start();
//		new Thread(new Terminated(), "Terminated").start();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("................");
	}
	
	static class New implements Runnable {
		
		public void run() {
			while (true) {
				System.out.println("NEW");
			}
		}
	}
	
	static class RunnableState implements Runnable {
		
		public void run() {
			while (true) {
				// Thread.yield();
				System.out.println("Runnable");
			}
		}
	}

	static class Blocked implements Runnable {

		public void run() {
			// 给对象加锁但一直不释放锁，别的线程访问不了
			synchronized (Blocked.class) {
				while (true) {
					System.out.println("Blocked");
				}
			}
		}
	}

	static class Waiting implements Runnable {

		public void run() {
			while (true) {
				synchronized (Waiting.class) {
					try {
						System.out.println("Waiting wait start----");
						Waiting.class.wait(); // wait的前提必须是加锁 // - locked <0x000000079561c498> (a java.lang.Class for com.liuzhuang.thread.ThreadState$Waiting)
						System.out.println("Waiting wait end----");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	static class TimeWaiting implements Runnable {

		public void run() {
			// 第一种方式
//			try {
//				Thread.sleep(10000); // waiting on condition
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			// 第二种方式
			synchronized (TimeWaiting.class) {
				try {
					TimeWaiting.class.wait(100000); // in Object.wait()
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}
			System.out.println("TimeWaiting run end----");
		}
	}
	
	static class Terminated implements Runnable {

		public void run() {
			System.out.println("Termicated");
		}
	}

}
