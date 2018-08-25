package com.liuzhuang.thread;

/**
 * 对线程执行 interrupt() 
 * 1、不是中断线程，而是修改标志位、自己在程序中判断标志位
 * 2、有一个Api在使用时会捕获InterruptedException，补货之后便会把标志位恢复，说明这一次中断我已经处理过了
 * 3、没有判断标志位则标志位一直为true
 * 4、使用Thread.interrupted()判断标志位之后会进行复位，代表这一次中断我已经处理过了
 * 5、线程并不会终止
 * 
 * 是否消耗这次中断
 */
public class Interrupted {

	
	public static void main(String[] args) {
		Thread sleep = new Thread(new SleepRunner());
		sleep.setDaemon(true);
		
		Thread busy = new Thread(new BusyRunner());
		busy.setDaemon(true);
		
		sleep.start();
		busy.start();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// 修改标志位
		sleep.interrupt();
		busy.interrupt();
		
		System.out.println("sleep :" + sleep.isInterrupted());
		System.out.println("busy :" + busy.isInterrupted());
		
		try {
			Thread.sleep(5000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	static class SleepRunner implements Runnable {
		public void run() {
			while (true) {
				try {
					Thread.sleep(1000); // 如果正在睡眠过程中 中断了线程，会抛出异常，并会把 slepp.isInterrupted 标志位复位，反之不抛出异常标志位还是true
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
					//e.printStackTrace();
				}
//				System.out.println("sleep run");
			}
		}
	}
	
	static class BusyRunner implements Runnable {
		public void run() {
			while (true) {
				
				// 第一种 不会复位
//				空代码
				
				// 第二种 会复位
				if (Thread.interrupted()) {
					System.out.println("busy run interrupted");
				} else {
					// System.out.println("busy run");
				}
				
				// 第三种 不会复位
//				if (Thread.currentThread().isInterrupted()) {
//					System.out.println("busy run interrupted");
//				} else {
//					// System.out.println("busy run");
//				}
			}
		}
	}
}
