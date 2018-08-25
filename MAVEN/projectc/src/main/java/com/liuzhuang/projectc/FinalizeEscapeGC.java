package com.liuzhuang.projectc;

/**
 * 垃圾回收机制，
 * 对于重写了finalize方法的实例，回收一个对象至少要经历两次标记过程
 * 第一次：垃圾回收器通过可达性判断没有与GCRoots向连接的引用链会进行标记一次，另外如果有重写finalize方法， 并且第一次执行，会把它加入F-Queue队列中
 * 第二次：使用Finalize线程扫描F-Queue队列，如果没有可达性没有引用再次标记回收，如果又有了引用链关系，便会从F-Queue队列中移除，不会回收它。
 * 
 * 如果没有重写finalize方法，会经理一次标记
 * 第一次：垃圾回收器通过可达性判断没有与GCRoots向连接的引用链会进行标记一次
 */
public class FinalizeEscapeGC {

	public static FinalizeEscapeGC SAVE_HOOK = null;

	public void isAlive() {
		System.out.println("i am is still alive!");
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		FinalizeEscapeGC.SAVE_HOOK = this;
	}

	public static void main(String[] args) throws InterruptedException {
		SAVE_HOOK = new FinalizeEscapeGC();
		SAVE_HOOK = null;
		System.gc();
		Thread.sleep(500);

		if (SAVE_HOOK != null) {
			SAVE_HOOK.isAlive();
		} else {
			System.out.println("die");
		}

		SAVE_HOOK = null;
		System.gc();
		Thread.sleep(500);
		if (SAVE_HOOK != null) {
			SAVE_HOOK.isAlive();
		} else {
			System.out.println("die");
		}
	}

}
