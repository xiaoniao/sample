package com.liuzhuang.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock Condition 生产者消费模式
 * 
 * await signal
 */
public class ConditionUseCase {

	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();

	public void conditionWait() throws InterruptedException {
		System.out.println("conditionWait lock");
		lock.lock();
		try {
			System.out.println("conditionWait await");
			condition.await();
			System.out.println("conditionWait await callback");
		} finally {
			lock.unlock();
			System.out.println("conditionWait unlock");
		}
	}

	public void conditionSignal() {
		System.out.println("conditionSignal lock");
		lock.lock();
		try {
			System.out.println("conditionSignal signal");
			condition.signal();
		} finally {
			System.out.println("conditionSignal unlock");
			lock.unlock();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ConditionUseCase conditionUseCase = new ConditionUseCase();
		
		new Thread(() -> {
			try {
				conditionUseCase.conditionWait();//阻塞
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();

		Thread.sleep(2000);
		new Thread(() -> {
			conditionUseCase.conditionSignal();
		}).start();
	}

}
