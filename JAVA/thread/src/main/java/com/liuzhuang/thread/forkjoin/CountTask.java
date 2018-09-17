package com.liuzhuang.thread.forkjoin;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Fork join
 * 
 * Recursive 递归
 * 
 */
public class CountTask extends RecursiveTask<Integer> {
	private static final long serialVersionUID = -1979760246149667243L;

	private static final int THRESHOLD = 2; // 阀值
	private int start;
	private int end;

	private static HashMap<String, Integer> countThreadNameHashMap = new HashMap<>();

	public CountTask(int start, int end) {
		// System.out.println("CountTask start:" + start + " end:" + end);
		this.start = start;
		this.end = end;
	}

	/**
	 * 计算结果
	 */
	@Override
	protected Integer compute() {
		int sum = 0;
		boolean canCompute = end - start < THRESHOLD;
		if (canCompute) {
			// 递归结束条件
			for (int i = start; i <= end; i++) {
				int count = countThreadNameHashMap.getOrDefault(Thread.currentThread().getName(), 0);
				countThreadNameHashMap.put(Thread.currentThread().getName(), count + 1);

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				sum += i;
			}
		} else {
			// 拆分子任务
			int middle = (start + end) / 2;
			CountTask leftCount = new CountTask(start, middle);
			CountTask rightCount = new CountTask(middle + 1, end);
			leftCount.fork(); // ** fork 调用的是compute
			rightCount.fork();
			int leftResult = leftCount.join(); // ** join
			int rightResult = rightCount.join();
			sum = leftResult + rightResult;
		}
		return sum;
	}

	// 1 + 2 + 3 + 4
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		CountTask countTask = new CountTask(1, 100);
		forkJoinPool.submit(countTask);
		int result = countTask.get();
		System.out.println("result:" + result);

		for (Map.Entry<String,Integer> entry : countThreadNameHashMap.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}

		Thread.sleep(100000);
	}
}
