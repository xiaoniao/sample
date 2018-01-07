package com.liuzhuang.algorithmstructure;

import java.util.Arrays;

/**
 * 希尔排序 http://baike.baidu.com/item/%E5%B8%8C%E5%B0%94%E6%8E%92%E5%BA%8F
 * 保证有一定间隔的数组是排序的，基于插入排序。
 */
public class Shell {

	public static void main(String[] args) {
		int[] a = { 5, 4, 3, 2, 1, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		sort(a);
		System.out.println(Arrays.toString(a));
	}

	public static void sort(int[] a) {
		int n = a.length;
		int h = 1;
		while (h < n / 3) {
			h = 3 * h + 1;
		}
		while (h >= 1) {
			System.out.println("h=" + h);
			for (int i = h; i < n; i++) {
				for (int j = i; j >= h && a[j] < a[j - h]; j -= h) {
					int temp = a[j];
					a[j] = a[j - h];
					a[j - h] = temp;
				}
			}
			h = h / 3;
		}
	}
}
