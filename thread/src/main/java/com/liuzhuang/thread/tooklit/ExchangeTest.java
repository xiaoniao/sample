package com.liuzhuang.thread.tooklit;

import java.util.concurrent.Exchanger;

/**
 * 两个线程间交换数据
 * Exchanger
 */
public class ExchangeTest {
	
	static Exchanger<String> exchanger = new Exchanger<String>();
	
	public static void main(String[] args) {
		
		new Thread(new Runnable() {
			
			public void run() {
				try {
					String B = exchanger.exchange("this is A");
					System.out.println("Thread A:" + B);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			
			public void run() {
				try {
					String A = exchanger.exchange("this is B");
					System.out.println("Thread B:" + A);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

}
