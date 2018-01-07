package com.liuzhuang.threadpool;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * SynchronousQueue
 * 两个操作 put take，两个方法均是阻塞方法，队列中每时每刻最多只能有一个元素。
 * 一个线程添加元素当前线程阻塞，直到另一个线程消耗获取到这个元素，当前线程释放阻塞。
 * 一个线程获得元素当前线程阻塞，直到另一个线程插入一个元素到队列，当前线程释放阻塞，获得返回值。
 * 
 * 未看参考资料：
 *    SynchronousQueue
 *      http://blog.csdn.net/hudashi/article/details/7076814
 *    Java并发包中的同步队列SynchronousQueue实现原理
 *      http://ifeve.com/java-synchronousqueue/
 */
public class SynchronousQueues {

	public static void main(String[] args) {
		SynchronousQueue<Long> synchronousQueue = new SynchronousQueue<>();

		// 生产线程
		new Thread(() -> {
			try {
				// 添加元素到队列，阻塞，直到其他线程接收到这个元素
				synchronousQueue.put(1L);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();

		// 消费线程
		new Thread(() -> {
			try {
				// 获取并移除队列中的头元素，如果没有元素，会一直阻塞
				// System.out.println(synchronousQueue.take());
				// 获取并移除队列中的头元素，如果没有元素，会一直阻塞，增加了超时模式
				System.out.println(synchronousQueue.poll(2000, TimeUnit.NANOSECONDS));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
}
