package com.liuzhuang.algorithmstructure;

/**
 * 素数（质数）
 */
public class Prime {

	public static void main(String[] args) {
		System.out.println(isPrime(49));
	}

	/**
	 * 判断是否是素数（质数）
	 */
	public static boolean isPrime(int a) {
		if (a < 2) {
			return false;
		}
		for (int i = 2; i * i <= a; i++) { // 为什么是 i * i <= a ? 一半一半 防止重复循环
			if (a % i == 0) {
				System.out.println(i);
				return false;
			}
		}
		return true;
	}
}
