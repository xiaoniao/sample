package com.liuzhuang.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * 这是例子好像是错的
 */
public class WaitNotifySample {
	
	// 考虑如何对list进行同步 
	static List<String> list = new ArrayList<String>();
	static Object lock = new Object();

	public static void main(String[] args) throws InterruptedException {
		new Thread(new Customer()).start();
		Thread.sleep(1000);

		Producter p = new Producter();
		new Thread(p).start();
		Thread.sleep(100);

		System.out.println("====");

		int count = 10000;
		while (count > 0) {
			if (count % 2 == 0) {
				p.add("" + count);
				Thread.sleep(100);
			}
			count--;
		}
	}

	/**
	 * 消费者
	 */
	static class Customer implements Runnable {

		public void run() {
			while (true) {
				synchronized (lock) {
					System.out.println("消费者");
					
					// 实际的阻塞队列是使用Lock进行加锁而不是synchronized
					int size = 0;
					synchronized (list) {
						size = list.size();
					}
					
					if (size == 0) {
						try {
							lock.wait();
						} catch (InterruptedException e) {

						}
					} else {
						synchronized (list) {
							for (String string : list) {
								System.out.println(string);
							}
							list.clear();
						}
					}
				}
			}
		}
	}

	/**
	 * 生产者
	 */
	static class Producter implements Runnable {
		private volatile boolean isNotify = false;

		public void run() {
			// 注意不能 synchronized (lock) while (true) 这样会永远不释放锁
			while (true) {
				synchronized (lock) {
					if (isNotify) {
						lock.notifyAll(); // 这种用法错了，应该是在add的时候notify而不是再单独开一个循环进行通知
						isNotify = false;
					}
				}
			}
		}

		public void add(String string) {
			synchronized (list) {
				list.add(string);
				isNotify = true;
			}
		}
	}
}
