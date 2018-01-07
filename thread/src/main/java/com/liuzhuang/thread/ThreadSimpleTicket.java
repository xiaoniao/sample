package com.liuzhuang.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSimpleTicket implements Runnable {

//	private Integer tickets = 20;
	private AtomicInteger tickets = new AtomicInteger(20);

	public void run() {
		for (int i = 0; i < 20; i++) {
//			synchronized (tickets) {
//				if (tickets == 0) {
//					System.out.println("卖晚了");
//					continue;
//				}
//				tickets--;
//				System.out.println("卖了" + (20 - tickets) + "张票剩余" + tickets);
//			}
			if (tickets.get() > 0) {
				System.out.print("卖了" + (tickets.getAndDecrement()));
				System.out.println("剩余" + tickets.get());
			}
		}
	}

	public static void main(String[] args) {
		ThreadSimpleTicket t = new ThreadSimpleTicket();
		for (int i = 0; i < 10; i++) {
			new Thread(t).start();
		}
//		new Thread(new FutureTask<>(null));
	}
}
