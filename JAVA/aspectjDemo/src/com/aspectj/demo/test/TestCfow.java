package com.aspectj.demo.test;

public class TestCfow {

	public void foo() {
		System.out.println("foo");
	}

	public void bar() {
		foo();
		System.out.println("bar");
	}

	/**
	 * foo
	 * bar
	 * foo
	 */
	public void testMethod() {
		bar();
		foo();
	}

	public static void main(String[] args) {
		TestCfow testCfow = new TestCfow();
		testCfow.testMethod();
	}
}
