package com.liuzhuang.projectc;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

/**
 * 引用是指的在栈中变量指向的一个堆地址. 而引用类型,代表引用的关系的强弱,相对于对jvm的垃圾回收期来说.<br>
 * 强引用<br>
 * 软引用<br>
 * 弱引用 <br>
 * 虚引用<br>
 * 用代码证明它的功能作用
 * 用代码证明它的实际用途
 * 引用和JVM的关系
 */
public class App {

	private static final int _1MB = 1024 * 1024;
	private static boolean isRun = true;

	public static void main(String[] args) throws InterruptedException {
		/*App app = new App();
		app.referenceQueue();*/
		try {
			App app = App.class.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	private void hardReference() {
		Byte[] bytes = new Byte[2 * _1MB];
	}

	private void softReference() {
		SoftReference<Byte[]> softReference = new SoftReference<Byte[]>(null);
	}

	private void weakReferenct() {
		WeakReference<Byte[]> waReference = new WeakReference<Byte[]>(null);
	}

	private void phantomReference() {
		ReferenceQueue<Byte[]> referenceQueue = new ReferenceQueue<>();
		PhantomReference<Byte[]> phantomReference = new PhantomReference<Byte[]>(null, referenceQueue);
	}
	
	/**
	 * 用于调试垃圾回收
	 * 因为垃圾回收器检测到对象的可达性发生变化后，便会把它加入到ReferenceQueue中
	 */
	@SuppressWarnings("unused")
	private void referenceQueue() throws InterruptedException {
		//String value = "hello";
		String value = new String("world");
		ReferenceQueue<String> referenceQueue = new ReferenceQueue<>();
		PhantomReference<String> phantomReference = new PhantomReference<String>(value, referenceQueue);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (isRun) {
					Object object = referenceQueue.poll();
					if (object != null) {
						try {
							Field referent = Reference.class.getDeclaredField("referent");
							referent.setAccessible(true);
							Object result = referent.get(object);
							System.out.println("gc collect " + result.getClass() + " @" + result.hashCode() + " - " + result);
						} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
		value = null;
		Thread.sleep(1000);
		System.gc();
		Thread.sleep(1000);
		isRun = false;
		System.out.println("end!");
	}
}
