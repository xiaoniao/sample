package com.aspectj.demo.test;

/**
 * https://eclipse.org/aspectj/sample-code.html
 */
public class HelloWorld {

	public static int main(int i) {
		System.out.println("in the main method i = " + i);
		return i;
	}

	public static int age(int i) {
		return i;
	}

	public static void throwTest() throws NullPointerException {
		throw new NullPointerException();
	}

	public static void main(String[] args) {
		System.out.println(main(3));;
		// age(1);
		// throwTest();
	}
}
