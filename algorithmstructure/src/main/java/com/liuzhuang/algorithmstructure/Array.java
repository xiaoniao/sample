package com.liuzhuang.algorithmstructure;

import java.util.Arrays;

/**
 * 数组相关
 */
public class Array {

	public static void main(String[] args) {
		int[] a = { 10, 29, 78, 1, 30 };
		max(a);
		avg(a);
		copy(a);
		reverse(a);
		int[][] c = new int[][] { {}, {} };
		matrix(c);
		binaryString(4);
	}

	// 找出数组中最大的元素
	public static void max(int[] a) {
		int max = a[0];
		for (int i = 1; i < a.length; i++) {
			if (a[i] > max) {
				max = a[i];
			}
		}
		System.out.println("max:" + max);
	}

	// 计算数组元素的平均值
	public static void avg(int[] a) {
		double sum = 0.0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
		}
		System.out.println("avg:" + sum / a.length);
	}

	// 复制数组
	public static void copy(int[] a) {
		int[] c = new int[a.length];
		for (int i = 0; i < c.length; i++) {
			c[i] = a[i];
		}
		System.out.println("copy:" + Arrays.toString(c));
	}

	// 颠倒数组元素的顺序
	public static void reverse(int[] a) {
		for (int i = 0; i < a.length / 2; i++) {
			int temp = a[i];
			a[i] = a[a.length - 1 - i];
			a[a.length - 1 - i] = temp;
		}
		System.out.println("reverse:" + Arrays.toString(a));
	}

	// 矩阵相乘
	public static void matrix(int[][] a) {
		// double[][] c = new double[a.length][a.length];
		// for (int i = 0; i < a.length; i++) {
		// for (int j = 0; j < a.length; j++) {
		// for (int k = 0; k < a.length; k++) {
		// c[i][j] += a[i][k] * b[k][j];
		// }
		// }
		// }
	}

	public static void binaryString(int n) {
		String s = "";
		for (int i = n; i > 0; i /= 2) {
			s += i % 2;
		}
		System.out.println(reverse(s));
	}
	
	// 反转字符串
	public static String reverse(String s) {
		char[] c = s.toCharArray();
		for (int i = 0; i < c.length / 2; i++) {
			char temp = c[i];
			c[i] = c[c.length - 1 - i];
			c[c.length - 1 - i] = temp;
		}
		return new String(c);
	}
}
