package com.liuzhuang.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 查看程序运行的所有线程
 */
public class MultiThread {

	public static void main(String[] args) {
		startThread();
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
		for (ThreadInfo threadInfo : threadInfos) {
			System.out.println(threadInfo.getThreadId() + " - " + threadInfo.getThreadName() + " - " + threadInfo.getThreadState());
		}
		
		try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static void startThread() {
		int count = 1;
		while (count < 4) {
			Thread thread = new Thread(new Runnable() {
				
				public void run() {
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("----------");
				}
			}, "thread" + count);
			thread.start();
			count++;
		}
	}
}
