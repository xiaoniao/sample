package com.liuzhuang.thread.threadpool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job>{

	private static final int MAX_WORKER_NUMBERS = 10;
	private static final int DEFAULT_WORKER_NUMBERS = 5;
	private static final int MIN_WORKER_NUMBERS = 1;

	private int workerNum = DEFAULT_WORKER_NUMBERS;
	private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>()); // 线程安全
	private final LinkedList<Job> jobs = new LinkedList<Job>(); // 访问的时候要加锁保证线程安全
	
	private AtomicLong threadNum = new AtomicLong(); // 工作线程计数
	
	public DefaultThreadPool() {
		initializeWorkers(DEFAULT_WORKER_NUMBERS);
	}
	
	public DefaultThreadPool(int num) {
		workerNum = num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : num < MIN_WORKER_NUMBERS ? MIN_WORKER_NUMBERS : num;
		initializeWorkers(workerNum);
	}
	
	private void initializeWorkers(int num) {
		for (int i = 0; i < num; i++) {
			Worker worker = new Worker();
			workers.add(worker);
			Thread thread = new Thread(worker, "worker-" + threadNum.incrementAndGet());
			thread.start();
		}
	}
	
	public void execute(Job job) {
		synchronized (jobs) {
			jobs.addLast(job);
			jobs.notify();
		}
	}

	public void shutdown() {
		for (Worker worker : workers) {
			worker.shutdown();
		}
	}

	public void addWorkers(int num) {
		synchronized (jobs) {
			if (num + this.workerNum > MAX_WORKER_NUMBERS) {
				num = MAX_WORKER_NUMBERS - this.workerNum;
			}
			initializeWorkers(num);
			this.workerNum += num;
		}
	}

	public void removeWorker(int num) {
		// 这种方式需要等待Worker释放jobs锁
		synchronized (jobs) {
			System.out.println("removeWorker");
			if (num > this.workerNum) {
				throw new IllegalArgumentException("beyond worknum");
			}
			while (num > 0) {
				Worker worker = workers.get(0);
				if (workers.remove(worker)) {
					worker.shutdown();
					num--;
					this.workerNum--;
				}
			}
		}
	}
	
	public int getWorkerNum() {
		return workerNum;
	}

	public int getJobSize() {
		return jobs.size();
	}
	
	class Worker implements Runnable {

		private volatile boolean running = true;
		
		public void run() {
			while (running) {
				Job job = null;
				// 加锁
				synchronized (jobs) {
					// 空队列则一直等待
					while (jobs.isEmpty()) {
						try {
							jobs.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					// 获取待执行任务
					job = jobs.removeFirst();
				}
				// 退出锁，执行任务Runnable
				if (job != null) {
					try {
						System.out.println(Thread.currentThread().getName() + "  --start");
						job.run();
						System.out.println(Thread.currentThread().getName() + "  --end");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			System.out.println(Thread.currentThread().getName() + "线程终止!");
		}
	
		public void shutdown() {
			running = false;
		}
	}
}
