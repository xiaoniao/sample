package com.liuzhuang.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 
 * ExecutorService提供了关闭线程池的功能，并且会把Runnable和Callable包装成FutureTask来执行，扩展出了功能，获取返回值和捕获异常。
 * 
 * 其中主要操作是是：线程池中的队列，线程池中运行的线程，线程池的状态
 * 
 * void shutdown();
 * 1、拒绝新增任务，在队列中的任务和正在执行的任务继续执行
 * 2、任务执行完毕，线程死亡
 * 
 * List<Runnable> shutdownNow();
 * 1、拒绝新增任务，还在队列中的任务会被清空，对正在执行的任务的线程发出InterruptedException中止信号
 * 2、任务执行完毕，线程死亡
 * 
 * boolean isShutdown();
 * 1.不是正在运行中返回true
 * 
 * boolean isTerminated();
 * 1.判断线程池状态是否为TERMINATED
 * 
 * boolean awaitTermination(long timeout, TimeUnit unit) throws　InterruptedException;
 * 1.阻塞着等待线程池结束
 * 
 * <T> Future<T> submit(Callable<T> task);
 * 1.执行任务，可获得任务执行结果，和可捕获异常
 * 
 * <T> Future<T> submit(Runnable task, T result);
 * 1.执行任务，可获得result结果，和可捕获异常
 * 
 * Future<?> submit(Runnable task);
 * 1.执行任务，无返回结果，可捕获异常
 * 
 * <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException;
 * 1.批量执行任务
 * 2.等到所有任务执行完毕或者抛出异常，才会返回
 * 
 * <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException;
 * 1.批量执行任务
 * 2.任务超时没有完成会发送中断信号进行取消
 * 
 * <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException;
 * 1.批量执行任务，只获取最先执行完成的结果
 * 2.返回一批任务中最早执行完的，其他没完成执行的则不再执行
 * 
 * <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException;
 * 1.增加超时判断
 */
public class ExecutorServiceAPI {
	
	
	public static class Task implements Callable<Long> {

		@Override
		public Long call() throws Exception {
			System.out.println("Task");
			return 1L;
		}
	}
	
	public static class LongTimeTask implements Callable<Long> {
		
		@Override
		public Long call() throws Exception {
			System.out.println("LongTimeTask");
			Thread.sleep(10000);
			return 2L;
		}
	}
	
	public static class ExtraTask implements Callable<Long> {
		
		@Override
		public Long call() throws Exception {
			System.out.println("ExtraTask");
			Thread.sleep(10000);
			return 3L;
		}
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// shutdown();
		// shutdownNow();
		// isShutdownAndIsTerminated();
		// submitRunnableAndResult();
		// invoke();
		cancel();
	}
	
	/*
	 * shutdown
	 * 1、拒绝新增任务，在队列中的任务和正在执行的任务继续执行
	 * 2、任务执行完毕，线程死亡
	 * 
	 * awaitTermination
	 * 1.阻塞着等待线程池结束
	 */
	public static void shutdown() throws InterruptedException {
		ExecutorService service = Executors.newFixedThreadPool(4);
		service.submit(new LongTimeTask());//正在执行的任务
		service.submit(new LongTimeTask());//正在执行的任务
		service.submit(new LongTimeTask());//正在执行的任务
		service.submit(new LongTimeTask());//正在执行的任务
		service.submit(new ExtraTask());//在队列中的任务
		
		service.shutdown();//待分析中断原理
		try {
			service.submit(new Task());
		} catch (Exception e) {
			System.out.println("禁止提交新任务");
		}
		
		while (!service.awaitTermination(1, TimeUnit.SECONDS)) {
			System.out.println("线程池没有关闭");
		}
		System.out.println("线程池已经关闭");
	}
	
	/*
	 * 1、拒绝新增任务，还在队列中的任务会被清空，对正在执行的任务的线程发出InterruptedException中止信号
	 * 2、任务执行完毕，线程死亡
	 */
	public static void shutdownNow() throws InterruptedException {
		ExecutorService service = Executors.newFixedThreadPool(4);
		Future<Long> future1 = service.submit(new LongTimeTask());//正在执行的任务
		Future<Long> future2 = service.submit(new LongTimeTask());//正在执行的任务
		Future<Long> future3 = service.submit(new LongTimeTask());//正在执行的任务
		Future<Long> future4 = service.submit(new LongTimeTask());//正在执行的任务
		Future<Long> future5 = service.submit(new ExtraTask());//在队列中的任务
		
		List<Runnable> inQueue = service.shutdownNow();//待分析中断原理
		for (Runnable runnable : inQueue) {
			System.out.println("还在队列中的任务：" + runnable);
		}
		try {
			service.submit(new Task());
		} catch (Exception e) {
			System.out.println("禁止提交新任务");
		}
		
		while (!service.awaitTermination(1, TimeUnit.SECONDS)) {
			System.out.println("线程池没有关闭");
		}
		
		System.out.println("线程池已经关闭");
		
		printReulst(future1, "future1");
		printReulst(future2, "future2");
		printReulst(future3, "future3");
		printReulst(future4, "future4");
		printReulst(future5, "future5");
		System.out.println("die");
	}

	/**
	 * 打印任务
	 */
	public static void printReulst(Future<Long> future, String tag) {
		try {
			future.get(1, TimeUnit.SECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			System.out.println(tag + " : " + e.getMessage());
		}
	}
	
	/**
	 * shutdown 是否执行过，只要线程池状态不是RUNNING状态就返回true
	 *   RUNNING
	 *   SHUTDOWN
	 *   STOP
	 *   TIDYING
	 *   TERMINATED
	 * isTerminated 判断线程池状态是否为TERMINATED
	 */
	public static void isShutdownAndIsTerminated() {
		ExecutorService service = Executors.newFixedThreadPool(4);
		service.submit(new LongTimeTask());
		service.submit(new LongTimeTask());
		service.submit(new LongTimeTask());
		service.submit(new LongTimeTask());
		service.shutdown();//设置为SHUTDOWN状态
		//service.shutdownNow();//设置为STOP状态
		System.out.println("isShutDown : " + service.isShutdown());
		
		while (!service.isTerminated()) {
			
		}
		System.out.println("线程池终止");
	}
	
	/**
	 * Future<?> submit(Runnable task)
	 * 
	 * <T> Future<T> submit(Callable<T> task)
	 * 
	 * <T> Future<T> submit(Runnable task, T result)
	 */
	public static void submitRunnableAndResult() {
		ExecutorService service = Executors.newFixedThreadPool(4);
		
		//Runnbale: 捕获异常
		Future<?> f = service.submit(new Runnable() {
			
			@Override
			public void run() {
				int a = 1 / 0;
			}
		});
		try {
			System.out.println(f.get());
		} catch (InterruptedException | ExecutionException e) {
			System.out.println("Runnable 异常 : " + e.getMessage());
		}
		
		//Runable: 捕获异常，也可返回预先设置的结果。
		Future<String> stringf = service.submit(new Runnable() {
			
			@Override
			public void run() {
				//int a = 1 / 0;
			}
		}, "result");
		try {
			System.out.println(stringf.get());
		} catch (InterruptedException | ExecutionException e) {
			System.out.println("Runnable Result 异常 : " + e.getMessage());
		}
		
		//Callable捕获异常并获得返回值
		Future<Long> longF = service.submit(new Callable<Long>() {
			@Override
			public Long call() throws Exception {
				int a = 1 / 0;
				return 1L;
			}
		});
		try {
			System.out.println(longF.get());
		} catch (InterruptedException | ExecutionException e) {
			System.out.println("Callable 异常 : " + e.getMessage());
		}
	}
	
	public static void invoke() {
		ExecutorService service = Executors.newFixedThreadPool(4);
		List<Callable<Long>> tasks = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			tasks.add(new Callable<Long>() {
				@Override
				public Long call() throws Exception {
					long sleeptime = new int[] {500, 800 , 1000, 2000, 300, 100, 200, 400, 600, 700}[new Random().nextInt(10)];
					Thread.sleep(sleeptime);
					System.out.println("A2 " + sleeptime);
					//int a = 1 / 0;
					return sleeptime;
				}
			});
		}
		//invokeAll
		try {
			//等到所有任务执行完毕或者抛出异常，才会返回
			//List<Future<Long>> futures = service.invokeAll(tasks);
			//任务超时没有完成会发送中断信号进行取消
			List<Future<Long>> futures = service.invokeAll(tasks, 1, TimeUnit.SECONDS);
			System.out.println("futures size : " + futures.size());
			
			for (Future<Long> future : futures) {
				try {
					System.out.println(future.get());
				} catch (ExecutionException | CancellationException e) {
					System.out.println("异常:" + e);
				}
			}
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		
		//invokeAny
		try {
			// 返回一批任务中最早执行完的，其他没完成执行的则不再执行
			Long oneResult = service.invokeAny(tasks);
			System.out.println("oneResult : " + oneResult);
		} catch (InterruptedException | ExecutionException e) {
			System.out.println(e.getMessage());
		}		
		System.out.println("线程池仍在运行中");
	}
	
	/**
	 * Future<T>接口 取消任务
	 * 取消的任务分为两种
	 * 	1. 在队列中还未运行
	 *  2. 在线程中运行
	 * 如果在队列中则从队列中移除，如果是在运行中则发出中断线程信号，中断任务执行。
	 */
	public static void cancel() {
		ExecutorService service = Executors.newFixedThreadPool(4);
		Future<Long> f = service.submit(new LongTimeTask());
		service.submit(new LongTimeTask());
		service.submit(new LongTimeTask());
		service.submit(new LongTimeTask());
		Future<Long> queueF = service.submit(new ExtraTask());
		queueF.cancel(true);
		
		f.cancel(false);
		try {
			f.get();//抛出CancellationException异常
		} catch (InterruptedException | ExecutionException | CancellationException e) {
			System.out.println(e);
		}
		service.shutdown();
	}
}
