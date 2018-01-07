package com.liuzhuang.thread.tooklit;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 插孔开门
 */
public class CyclicBarrierTest {

	
//	static CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
//		
//		public void run() {
//			System.out.println("屏障解除");
//		}
//	});
	
	static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
	
	static String a = "";
	public static void main(String[] args) {
		
		new Thread(() -> {
			while (true) {
				String waitCoutn = String.valueOf(cyclicBarrier.getNumberWaiting());
				if (!a.contains(waitCoutn)) {
					a += waitCoutn;
					System.out.println(waitCoutn);
				}
			}
		}).start();
		
		new Thread(() -> {
			try {
				// 自定义线程调用并阻塞
				cyclicBarrier.await(); // 当await数量等于2时，会自动返回
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}).start();
		
		try {
			// main线程调用并阻塞
			cyclicBarrier.await();  // 当await数量等于2时，会自动返回
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.out.println("阻塞结束" + cyclicBarrier.getNumberWaiting());
		
		cyclicBarrier.reset();
	}
}
