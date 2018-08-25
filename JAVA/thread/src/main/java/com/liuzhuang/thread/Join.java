package com.liuzhuang.thread;

/**
 * join 方法让当前执行线程等待 join 线程执行结束，其原理是不停检查 join 线程是否存活着，如果 join 线程存活则让当前线程永远等待。其中，wait(0) 表示永远等待下去。
 * 
 * while (isAlive()) {
 *    wait(0);
 * }
 */
public class Join {

	public static void main(String[] args) {
		Thread previous = Thread.currentThread();
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(new Domino(previous), String.valueOf(i));
			thread.start();
			previous = thread;
		}
		System.out.println("main die");
	}
	
	static class Domino implements Runnable{
		
		private Thread preThread;
		
		public Domino(Thread preThread) {
			this.preThread = preThread;
		}

		public void run() {
			try {
				// Waits for this thread to die. 本质也是 等待/通知范式 调用的 wait()，当preThread线程终止的时候，会调用自身的notifyall()方法，会通知所有等待再该对象上的线程。
				System.out.println(Thread.currentThread().getName() + "A");
			  // 线程没有死,便一直等待,直到线程死掉,然后自动调用notifyAll,返回发现线程死了,便会停止循环
				preThread.join(); // 等待preThread die
			  System.out.println(Thread.currentThread().getName() + "B");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "terminate.");
		}
	}
}
