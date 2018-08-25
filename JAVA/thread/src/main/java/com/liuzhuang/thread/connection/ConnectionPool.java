package com.liuzhuang.thread.connection;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * 数据库连接池示例
 * 使用时从pool中删除,使用完后再加入到pool中.
 */
public class ConnectionPool {

	// 可用的数据库连接
	private LinkedList<Connection> pool = new LinkedList<Connection>();
	
	// 初始化数据库连个数
	public ConnectionPool(int initialSize) {
		if (initialSize > 0) {
			for (int i = 0; i < initialSize; i++) {
				pool.addLast(ConnectionDriver.createConnection());
			}
		}
	}
	
	// 归还数据库链接
	public void releaseConnection(Connection connection) {
		if (connection != null) {
			synchronized (pool) {
				pool.addLast(connection);
				pool.notifyAll();
			}
		}
	}
	
	// 获取数据库链接,在mills内无法获取到连接,将会返回null.
	public Connection fetchConnection(long mills) {
		synchronized (pool) {
			if (mills <= 0) {
				while (pool.isEmpty()) {
					try {
						pool.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				return pool.removeFirst();
			} else {
				long future = System.currentTimeMillis() + mills;
				long remaing = mills;
				
				// 当线程数量小于等于数据库连接数量，数据库连接够用,并不会执行while()语句
				// 超时策略
				while (pool.isEmpty() && remaing > 0) {
					try {
						pool.wait(remaing); // 最多等待remaing这么长时间
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					remaing = future - System.currentTimeMillis();
				}
				
				Connection result = null;
				if (!pool.isEmpty()) {
					result = pool.removeFirst();
				}
				return result;
			}
		}
	}
	
}
