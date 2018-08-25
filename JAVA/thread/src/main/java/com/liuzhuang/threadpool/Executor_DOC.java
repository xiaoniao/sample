package com.liuzhuang.threadpool;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;

/**
 * Executor
 * 
 * 执行提交上来的Runnable任务。
 * 这个接口解耦的任务的执行细节，比如线程的使用方式，scheduling线程调度。
 * 
 * #替代每次创建线程执行任务
 * Executor用来代替每次都要创建线程的方式，比如相比这样执行线程 new Thread(new RunnableTask()).start()，我们可以
 * 这样来替代:
 *  Executor executor = anExecutor;
 *  executor.execute(new RunnableTask1());
 *  executor.execute(new RunnableTask2());
 * 
 * #使用调用者线程中同步执行代码
 * 然而Executor接口也不一定要求都是异步执行任务，你也可以在调用者的线程中同步的执行代码
 *   class DirectExecutor implements Executor {
 *   	public void execute(Runnable r) {
 *      	r.run();
 *   	}
 *   }
 * 
 * #新建线程执行任务
 * 更常见的是任务不能在调用者的线程中执行，需要单独新建一个线程执行，例如：
 *   class ThreadPerTaskExecutor implements Executor {
 *   	public void execute(Runnable r) {
 *      	new Thread(r).start();
 *      }
 *   }
 *  
 * #线性执行任务
 * 一些功能强大的Executor实现类，更注重添加一些限制条件约束任务数量和线程数量，还决定任务什么时候执行。
 * 下面的例子展示线性的执行任务
 *   class SerialExecutor implements Executor {
 *      final Queue<Runnable> tasks = new ArrayDeque<Runnable>();
 *      final Executor executor;
 *      Runnable active;
 *  
 *      SerialExecutor(Executor executor) {
 *        this.executor = executor;
 *      }
 *  
 *      public synchronized void execute(final Runnable r) {
 *        tasks.offer(new Runnable() {
 *          public void run() {
 *            try {
 *              r.run();
 *            } finally {
 *              scheduleNext();
 *            }
 *          }
 *        });
 *        if (active == null) {
 *          scheduleNext();
 *        }
 *      }
 *  
 *      protected synchronized void scheduleNext() {
 *        if ((active = tasks.poll()) != null) {
 *          executor.execute(active);
 *        }
 *      }
 *    }
 * 
 * -----------------------------------------------------
 * 
 * void execute(Runnable command)
 * 在未来执行这个任务，不能保证立即执行。任务在那个线程中执行不确定，需要看子类怎么实现。
 * 可能在调用者的线程中执行，也可能创建一个新线程中执行，也可能放到一个线程池中执行。
 * 
 * 异常:
 *   RejectedExecutionException 如果无法接收任务时抛出异常
 *   NullPointerException 如果任务为null，则抛出此异常
 */
public class Executor_DOC {
	
	public static void main(String[] args) {
		
		// 常见的线程方式
		new Thread(new RunnableTask()).start();
		
		// 在主线程中执行任务(在调用者线程中执行任务)
		new DirectExecutor().execute(new RunnableTask());
		
		// 创建新线程执行
		new ThreadPerExecutor().execute(new RunnableTask());
		
		// 线性执行任务(使用递归取值执行)
		SerialExecutor serialExecutor = new SerialExecutor(new ThreadPerExecutor());
		for (int i = 0; i < 5; i++) {
			serialExecutor.execute(new RunnableTask());
		}
	}
	
	/**
	 * 同步执行任务，在调用者的线程中执行，不另外创建新线程
	 */
	static class DirectExecutor implements Executor {
		@Override
		public void execute(Runnable command) {
			command.run();
		}
	}
	
	/**
	 * 通过新线程执行任务
	 */
	static class ThreadPerExecutor implements Executor {
		@Override
		public void execute(Runnable command) {
			new Thread(command).start();
		}
	}
	
	/*
	 * 装饰器模式
	 * 线性执行任务
	 */
	static class SerialExecutor implements Executor {
		
		final Queue<Runnable> tasks = new ArrayDeque<>();
		final Executor executor;
		Runnable active;
		
		public SerialExecutor(Executor executor) {
			this.executor = executor;
		}
		
		@Override
		public void execute(Runnable command) {
			System.out.println("SerialExecutor execute");
			tasks.offer(() -> {
				try {
					command.run();
				} finally {
					scheduleNext();
				}
			});
			if (active == null) {
				System.out.println("SerialExecutor active == null");
				scheduleNext();
			}
		}
		
		/**
		 * execute和scheduleNext循环递归调用
		 */
		protected synchronized void scheduleNext() {
			System.out.println("SerialExecutor scheduleNext");
			if ((active = tasks.poll()) != null) {
				executor.execute(active); // 由实际的Executor执行
			} else {
				System.out.println("SerialExecutor scheduleNext active == null");
			}
		}
	}	

	/**
	 * 任务
	 */
	static class RunnableTask implements Runnable {
		@Override
		public void run() {
			System.out.println("RunnableTask");
		}
	}
	
	/**
	 * Runnable执行的两种方式
	 * 1.创建线程的时候把Runnable传进去（实际是在run方法中执行runnbale.run()）
	 * 2.在线程的Runnbale方法中执行
	 */
	private static void runnable_1(Runnable runnable) {
		new Thread(runnable).start();
	}
	
	private static void runnable_2(Runnable runnable) {
		new Thread() {
			@Override
			public void run() {
				runnable.run();
			}
		}.start();;
	}
}
