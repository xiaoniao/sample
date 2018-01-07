package com.liuzhuang.algorithmstructure;

import java.util.Arrays;

public class Algorith {

	public static void main(String[] args) {
		// 选择排序（把最小的放在当前排序数组的最左侧）
		// 123456 |______
		//  23566 _|_____
		//   3456 __|____
		//    456 ___|___
		//     56 ____|__
		//      6 _____|_
		int[] a = { 12, 66, 88, 55, 44, 22 };
		for (int i = 0; i < a.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < a.length; j++) {
				if (a[j] < a[min]) {
					min = j;
				}
			}
			int temp = a[i];
			a[i] = a[min];
			a[min] = temp;
		}
		System.out.println(Arrays.toString(a));

		// 冒泡排序
		a = new int[] { 12, 66, 88, 55, 44, 22 };
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length - 1 - i; j++) {
				// System.out.print(j);
				if (a[j + 1] < a[j]) {
					int temp = a[j + 1];
					a[j + 1] = a[j];
					a[j] = temp;
				}
			}
			// System.out.println();
		}
		System.out.println(Arrays.toString(a));

		// 插入排序
		a = new int[] { 12, 66, 88, 55, 44, 22 };
		for (int i = 1; i < a.length; i++) {
			for (int j = i - 1; j >= 0; j--) {
				System.out.println(Arrays.toString(a) + " || " + a[j + 1] + "  ---   " + a[j]);
				if (a[j + 1] < a[j]) {
					int temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
					System.out.println("交换");
				}
			}
		}
		System.out.println(Arrays.toString(a));
	}
}
