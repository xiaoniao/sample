package com.liuzhuang.thread;

public class Profiler {

	// 没有set 直接get的时候会调用 initialValue方法
	static ThreadLocal<Long> TIME_THREDLOCAL = new ThreadLocal<Long>() {
		protected Long initialValue() {
			System.out.println("aaa");
			return System.currentTimeMillis();
		};
	};

	public static final void begin() {
		TIME_THREDLOCAL.set(System.currentTimeMillis());
	}

	public static final long end() {
		return System.currentTimeMillis() - TIME_THREDLOCAL.get();
	}

	public static void main(String[] args) throws InterruptedException {
		Profiler.begin();
		Thread.sleep(1000);
		System.out.println(Profiler.end());
	}
}
