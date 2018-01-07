package com.liuzhuang.thread;

public class Yield {

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(new YieldRunnable(i));
			thread.start();
		}
		
		Thread.sleep(2000);
	}

	static class YieldRunnable implements Runnable {
		
		private int count;
		
		public YieldRunnable(int count) {
			this.count = count;
		}
		
		public void run() {
			System.out.println("A" + count);
			Thread.yield(); // 让出正在执行的机会 计入Ready状态，等待系统调用再次进入运行状态。
			System.out.println("B" + count);
		}
	}
}
