package com.liuzhuang.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 * 
 * Executor 线程池抽象接口，定义执行Runnable。 Executors 工具类，用于实例线程池。
 */
public class ThreadPools {

	static List<Runnable> runnables = createRunnable();

	public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
		// cachePool();
//		scheduledPool();
	}

	private static void cachePool() {
		System.out.println("--------newCachedThreadPool--------------");
		ExecutorService executor = Executors.newCachedThreadPool();
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
		threadPoolExecutor.setKeepAliveTime(2, TimeUnit.SECONDS);
		threadPoolExecutor.allowCoreThreadTimeOut(true);
		for (Runnable runnable : runnables) {
			executor.submit(runnable);
		}
	}

	private static void fixedPool() {
		System.out.println("--------newFixedThreadPool--------------");
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for (Runnable runnable : runnables) {
			executor.submit(runnable);
		}
	}
	
	private static void singlePool() {
		System.out.println("--------newSingleThreadExecutor--------------");
		ExecutorService executor = Executors.newSingleThreadExecutor();
		for (Runnable runnable : runnables) {
			executor.submit(runnable);
		}
	}

	private static void scheduledPool() {
		System.out.println("--------newScheduledThreadPool--------------");
		ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
		/*for (Runnable runnable : runnables) {
			service.submit(runnable);
		}*/
		
		//延迟执行 5秒钟后执行
		service.schedule(() -> {
			System.out.println("schedule");
			return 1L;
		}, 5, TimeUnit.SECONDS);
		
		//周期性的执行 5首次延迟时间 3之后延迟时间
		service.scheduleAtFixedRate(() -> {
			System.out.println("scheduleAtFixedRate");
		}, 5, 3, TimeUnit.SECONDS);
	}
	
	private static List<Runnable> createRunnable() {
		List<Runnable> result = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			final int threadCount = i;
			result.add(() -> {
				System.out.println(threadCount);
			});
		}
		return result;
	}
}
