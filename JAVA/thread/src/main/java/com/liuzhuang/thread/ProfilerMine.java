package com.liuzhuang.thread;

public class ProfilerMine {

	static ThreadLocal<Long> TIME_THREDLOCAL = new ThreadLocal<Long>();
	
	public static void main(String[] args) {
		
		for (int i = 0; i < 10; i++) {
			new Thread(new Single(i)).start();
		}
	}
	
	static class Single implements Runnable {
		
		private int count;
		
		public Single(int count) {
			this.count = count;
		}

		public void run() {
			TIME_THREDLOCAL.set(Long.valueOf(count));
			System.out.println(TIME_THREDLOCAL.get() + " - " + count);
		}
	}
}
