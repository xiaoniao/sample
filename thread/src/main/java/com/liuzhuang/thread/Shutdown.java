package com.liuzhuang.thread;

public class Shutdown {

	public static void main(String[] args) {
		Thread t = new Thread(new InterruptRunner());
		t.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		t.interrupt();
		
		try {
			t.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		FlagQuitRunner r2 = new FlagQuitRunner();
		Thread t2 = new Thread(r2);
		t2.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		r2.cancel();
	}
	
	static class InterruptRunner implements Runnable {
		public void run() {
			// Thread.interrupted()
			while (true && !Thread.currentThread().isInterrupted()) {
				
			}
			System.out.println("shutdown");
		}
	}
	
	static class FlagQuitRunner implements Runnable {
		
		private volatile boolean isQuit = false;
		
		public void run() {
			while (true && !isQuit) {
				
			}
			System.out.println("shutdown2");
		}
		
		public void cancel() {
			isQuit = true;
		}
	}
}
