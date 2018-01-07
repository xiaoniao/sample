package com.liuzhuang.projectc;

/**
 * 在栈中的变量会为了节省空间而会存在复用的问题，所以会导致如果没有被覆盖和置null，它并不会回收栈对应堆中的内存
 * 
 * 局部变量表中的 Slot是可重用的，方法体中定义的变量，作用域并不一定会覆盖整个方法体，
 * 如果当前字节码PC计数器的值已经超过了某个变量的作用域，那么这个变量对应的 Slot 就可以交给其他变量使用 (这就是为了节省空间而复用)
 * 
 * 因为超出变量的作用域后，它可能会交给别的变量使用，从而导致它对应的堆不会立即回收。
 * 
 * VM arguments -verbose:gc
 */
public class SlotGc {
	public static void main(String[] args) {
		{
			@SuppressWarnings("unused")
			byte[] holder = new byte[32 * 1024 * 1024];
			// holder = null;
			holder = new byte[1 * 1024 * 1024];
		}
		System.gc();
	}
}
