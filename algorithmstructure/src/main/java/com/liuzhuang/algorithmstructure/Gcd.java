package com.liuzhuang.algorithmstructure;

import edu.princeton.cs.algs4.StdDraw;

/**
 * greatest common divisor 最大公约数
 */
public class Gcd {

	public static void main(String[] args) {
		System.out.println("greastest common divisor");
		System.out.println(gcd(105, 24));
		System.out.println(gcd(24, 9));
		System.out.println(gcd(9, 6));
		System.out.println(gcd(6, 3));

		System.out.println("divisor");
		divisorNormal(90);
		System.out.println("divisor simple");
		divisor(90);
		
		//drawFunction();
	}

	/**
	 * 欧几里得算法 计算两个非负整数的最大公约数， 证明过程:
	 * http://v.youku.com/v_show/id_XMTM2NTE3NDU1Mg==.html?from=s1.8-1-1.2
	 * 
	 * @param p
	 * @param q
	 * @return
	 */
	public static int gcd(int p, int q) {
		if (q == 0) {
			return p;
		}
		int r = p % q;
		System.out.printf("p:%-5s | q:%-5s r:%-5s\n", p, q, r);
		return gcd(q, r);
	}

	/**
	 * 求公约数 版本1
	 */
	public static void divisorNormal(int a) {
		for (int i = 2; i < a; i++) {
			// System.out.println("i:" + i);
			if (a % i == 0) {
				System.out.println(i);
			}
		}
	}
	
	/**
	 * 求公约数 版本2 减少循环次数
	 */
	public static void divisor(int a) {
		for (int i = 2; i * i <= a; i++) {
			// System.out.println("i:" + i);
			if (a % i == 0) {
				System.out.println(i + " " + (a / i));
			}
		}
		// 为什么要 i * i
//		for (int i = 2; i < a; i++) {
//			System.out.println("i:" + i + " - " + Double.valueOf(a) / i);
//		}
	}
	
	public static void drawFunction() {
		int n = 100;
		StdDraw.setXscale(0, n);
		StdDraw.setYscale(0, n);
		for (int i = 2; i < n; i++) {
			StdDraw.point(i, 0);
			StdDraw.point(i, Double.valueOf(n) / i);
		}
	}
}
