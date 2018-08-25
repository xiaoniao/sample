package com.liuzhuang.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WaitNotify {

	static boolean flag = true;
	static Object lock = new Object();

	public static void main(String[] args) throws InterruptedException {
		new Thread(new Wait()).start();
		Thread.sleep(1000);
		new Thread(new Notify()).start();
	}

	/**
	 * 等待 消费者
	 */
	static class Wait implements Runnable {

		public void run() {
			synchronized (lock) {
				while (flag) {
					try {
						System.out.println(Thread.currentThread() + " flag is true. wait @" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
						lock.wait(); // 1、释放锁 进入 WAITING 状态
					} catch (InterruptedException e) {
						
					}
				}
				System.out.println(Thread.currentThread() + " flag is false. running @" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
			}
		}
	}

	/**
	 * 通知
	 */
	static class Notify implements Runnable {
		public void run() {
			synchronized (lock) {
				System.out.println(Thread.currentThread() + " hold lock. notify @" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
				lock.notifyAll();
				flag = false;
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {

				}
				System.out.println("当前释放锁 接着 lock.wait()返回重新进入Runnable状态");
				// 2、释放锁 接着 lock.wait()返回重新进入Runnable状态
			}
			synchronized (lock) {
				System.out.println(Thread.currentThread() + " hold lock again. sleep @" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {

				}
			}
		}
	}
}
