package com.juvenxu.mvnbook.helloworld2.array;

import java.util.Arrays;

/**
 * 声明数组      ：会在栈内存开辟变量
 * 初始化数组  ：会在堆内冲中开辟连续的空间 ，栈中的内存指向堆
 * 赋值数组      ： 堆中的内存中保存指向对象在堆中的地址
 * 
 * 数组是一个对象 数组指向一个连续内存地址的开始处 这些内存地址指向真正的引用地址
 */
public class ArrayAndCopy {

	public static void main(String[] args) {
		reference();
		System.out.print("\n");
		reAssignmentValue();
		System.out.print("\n");
		copy();
	}

	/**
	 * 引用问题
	 * 数组堆中 保存的是目标对象在堆中的引用
	 */
	private static void reference() {
		String name = "jack";
		String[] arrays = new String[] { name }; // 保存的是 "jack" 在堆中的地址
		name = "rose";
		System.out.println(Arrays.toString(arrays)); // 保存的是 "rose" 在堆中的地址
	}
	
	/**
	 * 重指派引用
	 * 数组重新赋值 是栈中的变量重新指向另一个连续的堆内存
	 */
	private static void reAssignmentValue() {
		String[] names = new String[] { "jack", "rose" };
		String[] languages = new String[] { "english", "chiness", "jpanese", "dutch" };
		names = languages;
		System.out.println(Arrays.toString(names));
	}

	/**
	 * 数组拷贝 实现底层数据拷贝 拷贝的也都是引用
	 * 
	 * Arrays.copyOf() 使用 System.arraycopy() 来实现
	 */
	private static void copy() {
		Member member = new Member("jack");
		Member[] members = new Member[] { member };

		Member[] copyMembers = Arrays.copyOf(members, 1);
		member.setName("rose");
		System.out.println(Arrays.toString(copyMembers));
	}

	public static class Member {
		private String name;

		public Member(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "Member [name=" + name + "]";
		}
	}
}
