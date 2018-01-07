package com.liuzhuang.threadpool;

public class Hello {

	interface A {
		void run() throws NullPointerException;
	}

	interface B extends A {
		void run();
	}

	static class C implements B {

		@Override
		public void run() {
			System.out.println("c run");
		}
	}

	static class D implements A {

		@Override
		public void run() throws NullPointerException {
			System.out.println("d run");
		}
	}

	static class E extends D {
		@Override
		public void run() {
			System.out.println("e run");
		}
	}

	public static void main(String[] args) {
		C c = new C();
		c.run();

		D d = new D();
		d.run();

		E e = new E();
		e.run();
	}
}
