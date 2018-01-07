package com.liuzhuang.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * 线程优先级
 * 运行结果 1的次数为 145255 10的次数为 145422 两者优先级执行次数相差不大
 */
public class Priority {

	private static volatile boolean notStart = true;
	private static volatile boolean notEnd = true;

	public static void main(String[] args) {
		List<Job> jobs = new ArrayList<Job>();

		for (int i = 0; i < 10; i++) {
			int priority = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
			Job job = new Job(priority);
			jobs.add(job);

			Thread thread = new Thread(job, "Thread" + i);
			thread.start();
		}
		notStart = false;

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		notEnd = false;

		for (Job job : jobs) {
			System.out.println(job.priority + " - " + job.jobCount);
		}
		
		long total = 0;
		for (int i = 0; i < 5; i++) {
			total += jobs.get(i).jobCount;
		}
		System.out.println(total / 5);
		
		total = 0;
		for (int i = 5; i < 10; i++) {
			total += jobs.get(i).jobCount;
		}
		System.out.println(total / 5);
	}

	static class Job implements Runnable {

		private int priority;
		private long jobCount;

		public Job(int priority) {
			this.priority = priority;
		}

		public void run() {
			while (notStart) {
				Thread.yield();
			}
			while (notEnd) {
				Thread.yield();
				jobCount++;
			}
		}
	}
}
