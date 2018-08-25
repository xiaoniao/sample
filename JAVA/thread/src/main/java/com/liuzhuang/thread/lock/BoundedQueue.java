package com.liuzhuang.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 模拟实现有边界队列
 */
public class BoundedQueue<T> {

	public static void main(String[] args) {
		BoundedQueue<String> queue = new BoundedQueue<>(2);
		try {
			queue.add("A");
			System.out.println("A");
			queue.add("B");
			System.out.println("B");
			queue.add("C");
			System.out.println("C");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			System.out.println(queue.remove());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private Object[] items;
	private int addIndex;
	private int removeIndex;
	private int count;

	private Lock lock = new ReentrantLock();
	private Condition notEmpty = lock.newCondition();
	private Condition notFull = lock.newCondition();

	public BoundedQueue(int size) {
		items = new Object[size];
	}

	/**
	 * 添加一个元素，如果数组满，则添加线程进入等待状态，直到有空位
	 */
	public void add(T t) throws InterruptedException {
		lock.lock();
		try {
			while (count == items.length) {
				notFull.await(); // 数组已满
			}
			items[addIndex] = t;
			if (++addIndex == items.length) {
				addIndex = 0;
			}
			count++;
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 由头部删除一个元素，如果数组为空，则删除线程进入等待状态，直到有新添加元素
	 */
	@SuppressWarnings("unchecked")
	public T remove() throws InterruptedException {
		lock.lock();
		try {
			while (count == 0) {
				notEmpty.await();// 数组为空
			}
			T result = (T) items[removeIndex];
			if (++removeIndex == items.length) {
				removeIndex = 0;
			}
			count--;
			notFull.signal();
			return result;
		} finally {
			lock.unlock();
		}
	}
}
