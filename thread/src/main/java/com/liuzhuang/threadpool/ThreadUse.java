package com.liuzhuang.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * #线程和任务
 * 
 * 先明白线程和任务的区别，线程就是Thread，只能用Thread类来表示，Runnbale只是一个任务，FutureTask也只是一个任务是一个有返回值并且可以捕获执行异常的任务。
 * 
 * 线程执行是交由JVM调度来执行 Thread#run() 方法，然后在run方法中去执行任务。抽象出来用代码这样表示
 * 
 * class Thread {
 *   public void run() {
 *     ...
 *     //自己写
 *     //Runnable
 *     //FutureTask
 *   }
 * }
 * 
 * 线程:Thread 
 * 任务:Runnable FutureTask(Callable)
 * 
 * 最后一句Runnable和Callable不是线程，它们是在线程中执行的任务。
 */
public class ThreadUse {

	// 用线程来执行任务的三种方式
	public static void main(String[] args) {
		m1();
		m2();
		m3();
	}

	/**
	 * 重写Thread run (我称之为自定义任务)
	 */
	private static void m1() {
		new Thread() {
			public void run() {
				System.out.println("thread run");
			};
		}.start();
	}

	/**
	 * Runnable 无返回值
	 */
	private static void m2() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("runnable");
			}
		}).start();
	}

	/**
	 * FutureTask 有返回值
	 */
	private static void m3() {
		Callable<Long> callable = new Callable<Long>() {
			@Override
			public Long call() throws Exception {
				Thread.sleep(5000);
				return System.currentTimeMillis();
			}
		};
		FutureTask<Long> futureTask = new FutureTask<>(callable);
		new Thread(futureTask).start();
		try {
			
			futureTask.cancel(true);
			futureTask.isCancelled();
			futureTask.isDone();
			
			System.out.println(futureTask.get()); //get()方法是阻塞的
			try {
				futureTask.get(3, TimeUnit.SECONDS);
			} catch (TimeoutException e) {
				e.printStackTrace();
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
