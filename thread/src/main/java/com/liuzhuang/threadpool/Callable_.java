package com.liuzhuang.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 
 *
 */
public class Callable_ {

	public static void main(String[] args) {

		// Callable
		Callable<Long> callable = new Callable<Long>() {

			@Override
			public Long call() throws Exception {
				System.out.println("call");
				Thread.sleep(5000);
				System.out.println(Thread.currentThread());
				return System.currentTimeMillis();
			}
		};

		// FutureTask
		FutureTask<Long> futureTask = new FutureTask<>(callable);
		new Thread(futureTask, "futureTask").start();

		/**
		 * Future
		 * 1.取消任务
		 * 2.任务是否被取消
		 * 3.任务是否完成
		 * 4.获取返回值
		 * 5.获取返回值附加超时限制
		 */
		
		boolean isCancel = futureTask.isCancelled();
		System.out.println("isCancel : " + isCancel);
		if (!isCancel) {
			futureTask.cancel(true);
		}
		boolean isDone = futureTask.isDone();
		System.out.println("isDone : " + isDone);

		// 获取返回结果（结果不会通过事件回调，需要自己手动获取返回结果）
//		try {
//			System.out.println(futureTask.get());// get()方法是阻塞的
//			futureTask.get(3, TimeUnit.SECONDS);
//		} catch (InterruptedException | ExecutionException | TimeoutException e1) {
//			e1.printStackTrace();
//		}
	}
}
