package com.juvenxu.mvnbook.helloworld2.array;

/**
 * 引用 和对象
 * 变量即别名
 */
public class Reference {

	public static void main(String[] args) {
		// Integer 中的 int value 是不可以被修改的 final
		Integer integer = new Integer(100);
		changevalue(integer);
		System.out.println(integer);

		// String 中的 char[] 也是不可以被修改的 final
		String string = new String("jack");
		changevalue(string);
		System.out.println(string);
	}

	private static void changevalue(Integer integer) {
		integer = 200; // 重新赋引用，不影响原指向对象
	}

	private static void changevalue(String string) {
		string = "rose"; // 重新赋引用，不影响原指向对象
	}
}
