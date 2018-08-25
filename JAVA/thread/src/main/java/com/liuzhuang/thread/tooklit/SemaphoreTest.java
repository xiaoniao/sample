package com.liuzhuang.thread.tooklit;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量
 * 红绿灯
 */
public class SemaphoreTest {

	private static final int THREAD_COUNT = 5;
	private static Executor executor = Executors.newFixedThreadPool(THREAD_COUNT);
	
	// 同时只允许3个线程访问，访问完之后要换回来+1
	private static Semaphore semaphore = new Semaphore(3);
	
	public static void main(String[] args) {
		for (int i = 0; i < THREAD_COUNT; i++) {
			executor.execute(new Runnable() {
				
				public void run() {
					System.out.println(Thread.currentThread() + " 可用资源数 : " + semaphore.availablePermits());
					try {
						semaphore.acquire(); // 
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					System.out.println(Thread.currentThread() + " 已获取访问权限");
					
					/**
					 * 模拟耗时业务操作
					 */
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					semaphore.release();
				}
			});
		}
	}
}
