package com.liuzhuang.thread.tooklit;

import java.util.concurrent.CountDownLatch;

/**
 * 倒计时阻塞器 
 * await() 大于0时阻塞 
 * countDown() 减1
 * 
 * 用于协调线程之执行先后顺序
 * 类似与 join
 */
public class CountDownLatchTest {

	static int count = 4;
	static CountDownLatch countDownLatch = new CountDownLatch(count);
	
	public static void main(String[] args) {
		
		new Thread(() -> {
			while (count > 0 ) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				countDownLatch.countDown(); // -1
				System.out.println("countDownLatch.getCount" + countDownLatch.getCount());
				count--;
			}
		}).start();
		
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("阻塞结束");
		
		example2();
	}
	
	private static void example2() {
//		countDownLatch = new CountDownLatch(4);
		try {
			System.out.println("await");
			countDownLatch.await();//阻塞 直到countDownLatch到0
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("example2");
	}
}
