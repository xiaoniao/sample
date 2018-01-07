package com.liuzhuang.thread.threadpool;

/**
 * 线程池
 */
public interface ThreadPool<Job extends Runnable> {

	// 执行一个 Job ，这个 job 需要实现 Runnable 接口
	void execute(Job job);
	
	// 关闭线程池
	void shutdown();
	
	// 增加工作者线程
	void addWorkers(int num);
	
	// 减少工作者线程
	void removeWorker(int num);
	
	// 得到正在等待执行的任务数量
	int getJobSize();
	
	// 获得工作线程数量
	int getWorkerNum();
}
