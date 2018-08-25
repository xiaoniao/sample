package com.liuzhuang.bigdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试N秒钟内，N个数据库连接，可以插入多少条数据。
 */
public class InsertEfficient {

	private static volatile int totalInsert = 0;

	public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
		System.out.println("insert:");
		for (int i = 0; i < 60; i++) {
			System.out.println("==========");
			totalInsert = 0;
			List<InsertThread> threads = multiConnection(i);
			Thread.sleep(1000);
			for (InsertThread thread : threads) {
				thread.interrupt();
			}
			System.out.println("1秒内," + i + "个连接,总插入条数：" + totalInsert);
		}
		System.out.println("select:");
		for (int i = 0; i < 60; i++) {
			System.out.println("==========");
			totalInsert = 0;
			List<SelectThread> threads = multiConnectionSelectThread(i);
			Thread.sleep(1000);
			for (SelectThread thread : threads) {
				thread.interrupt();
			}
			System.out.println("1秒内," + i + "个连接,总查询次数：" + totalInsert);
		}
	}

	private static List<InsertThread> multiConnection(int count) {
		List<InsertThread> threads = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			InsertThread runnable = new InsertThread();
			Thread thread = new Thread(runnable);
			thread.start();
			threads.add(runnable);
		}
		return threads;
	}
	
	private static List<SelectThread> multiConnectionSelectThread(int count) {
		List<SelectThread> threads = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			SelectThread runnable = new SelectThread();
			Thread thread = new Thread(runnable);
			thread.start();
			threads.add(runnable);
		}
		return threads;
	}

	/**
	 * 插入数据线程
	 */
	private static class InsertThread implements Runnable {

		private volatile boolean isInterrupt;

		@Override
		public void run() {
			Connection connection = null;
			try {
				// 获取一个数据库连接
				connection = DriverManager.getConnection("jdbc:mysql://192.168.1.33:3306/s1", "root", "b9xcc4z2");
				for (;;) {
					if (isInterrupt) {
						break;
					}
					PreparedStatement pstmt = connection.prepareStatement("insert into insertEfficient (title, content) values (?, ?)");
					pstmt.setString(1, "title");
					pstmt.setString(2, "content");
					pstmt.execute();
					totalInsert++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}

		public void interrupt() {
			isInterrupt = true;
		}
	}
	
	private static class SelectThread implements Runnable {

		private volatile boolean isInterrupt;

		@Override
		public void run() {
			Connection connection = null;
			try {
				// 获取一个数据库连接
				connection = DriverManager.getConnection("jdbc:mysql://192.168.1.33:3306/s1", "root", "b9xcc4z2");
				for (;;) {
					if (isInterrupt) {
						break;
					}
					PreparedStatement pstmt = connection.prepareStatement("select * from insertefficient where id = 1");
					pstmt.executeQuery();
					totalInsert++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}

		public void interrupt() {
			isInterrupt = true;
		}
	}
}
