package com.liuzhuang.thread;

public class WaitNotifyMine {
	
	private static Object o = new Object();

	public static void main(String[] args) throws InterruptedException {
		new Thread(new Wait("A")).start();
		Thread.sleep(1000);
		
		new Thread(new Wait("B")).start();
		Thread.sleep(1000);
		
		new Thread(new Notify()).start();
	}
	
	static class Wait implements Runnable {
		
		private String name;
		
		public Wait(String name) {
			this.name = name;
		}
		
		public void run() {
			
			synchronized (o) {
				while (true) {
					try {
						System.out.println(name + " wait.");
						o.wait(); // 1、释放锁 进入 WAITING 状态
					} catch (InterruptedException e) {
						
					}
					System.out.println(name + " notifyed");
				}
			}
		}
	}
	
	static class Notify implements Runnable {
		public void run() {
			synchronized (o) {
				//o.notify();
				o.notifyAll(); // 2、o上线程从 WAITING 状态变为 BLOCK 状态
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					
				}
				// 3、释放锁 接着 o.wait()返回重新进入Runnable状态
			}
		}
	}
}
