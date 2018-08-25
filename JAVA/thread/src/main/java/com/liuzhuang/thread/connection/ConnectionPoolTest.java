package com.liuzhuang.thread.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 演示在资源一定的情况下，并不是线程越多越好，就好比，10个人通过城门和1万人通过城门。
 */
public class ConnectionPoolTest {

	// 总共10个数据库链接
	static ConnectionPool pool = new ConnectionPool(10);
	
	// 计次阻塞类 countDown到0之后 await便不再阻塞
	static CountDownLatch start = new CountDownLatch(1); // 保证所有的ConnectionRunner能同时执行
	static CountDownLatch end; // 保证main线程在所有ConnectionRunner执行完成后才能执行
	
	public static void main(String[] args) {
		
		int threadCount = 11;
		end = new CountDownLatch(threadCount);
		
		int count = 100;
		AtomicInteger got = new AtomicInteger(); //获取到连接次数
		AtomicInteger notGot = new AtomicInteger(); //未获取到连接次数
		for (int i = 0; i < threadCount; i++) {
			Thread thread = new Thread(new ConnectionRunner(count, got, notGot), "ConnectionRunnerThread" + i);
			thread.start();
		}
		System.out.println("start.countDown()");
		start.countDown();
		try {
			end.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("total invoke:" + (threadCount * count));
		System.out.println("got connection:" + got);
		System.out.println("not got connection:" + notGot);
	}
	
	/**
	 * 
	 */
	public static class ConnectionRunner implements Runnable {
		
		int count;
		AtomicInteger got;
		AtomicInteger notGot;
		
		public ConnectionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
			super();
			this.count = count;
			this.got = got;
			this.notGot = notGot;
		}

		public void run() {
			try {
				System.out.println("start.await()");
				start.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("A");
			
			while (count > 0) {
				Connection connection = pool.fetchConnection(1000);
				if (connection != null) {
					try {
						connection.createStatement();
						connection.commit();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						pool.releaseConnection(connection);
						got.incrementAndGet();
					}
				} else {
					notGot.incrementAndGet();
				}
				count--;
			}
			
			end.countDown();
		}
	}
}
