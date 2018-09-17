package com.liuzhuang.thread.atomicc;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 */
public class Atomics {

	public void init() {

	}

	/**
	 * 原子更新基本类型
	 */
	public void testBasic() {
		AtomicBoolean aBoolean = new AtomicBoolean();
		AtomicInteger aInteger = new AtomicInteger();
		AtomicLong aLong = new AtomicLong();

		System.out.println(aBoolean.get());
		System.out.println(aInteger.get());
		System.out.println(aLong.get());
		boolean result = aInteger.compareAndSet(1, 10);
		System.out.println(result);
	}

	/**
	 * 原子更新数组
	 */
	public void testArray() {
		int[] array = { 1, 2, 3 };
		AtomicIntegerArray aIntegerArray = new AtomicIntegerArray(array);
		AtomicLongArray aLongArray;
		AtomicReferenceArray aReferenceArray;

		aIntegerArray.set(0, 11);
		System.out.println("index 0 : " + aIntegerArray.get(0));
		System.out.println("length : " + aIntegerArray.length());
	}

	/**
	 * 原子更新引用类型
	 */
	public void testReference() {
		AtomicReference<A> aReference = new AtomicReference<>();
		AtomicReferenceFieldUpdater aReferenceFieldUpdater;
		AtomicMarkableReference aMarkableReference;
		A a = new A("hello");
		aReference.set(a);
		aReference.compareAndSet(a, new A("world"));
		System.out.println(aReference.get().name);
	}

	/**
	 * 原子更新字段
	 */
	private void testField() {
		A a = new A("hello");
		AtomicStampedReference<A> aStampedReference = new AtomicStampedReference<Atomics.A>(a, 1);
		System.out.println(aStampedReference.getStamp());
	}

	public static class A {
		public String name;

		public A(String name) {
			this.name = name;
		}
	}

	public static void main(String[] args) {
		Atomics atomics = new Atomics();
		atomics.testBasic();
		atomics.testArray();
		atomics.testReference();
		atomics.testField();
	}

}
