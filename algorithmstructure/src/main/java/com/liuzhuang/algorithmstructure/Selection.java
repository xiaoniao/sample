package com.liuzhuang.algorithmstructure;

import java.util.Arrays;

/**
 * 选择排序
 * 首先，找出数组中最小的那个元素，其次，将它和数组的第一个元素交换位置（如果第一个元素就是最小元素，那么它就和自己交换）。
 * 再次，在剩下的元素中找到最小的元素，将它与数组的第二个元素进行交换位置，如此往复直到将整个数组排序。
 * 核心是把数组中的最小元素放到左侧，左边是已经排好序的。
 * 
 * 比较次数 n^2/2
 * 交换次数 N
 */
public class Selection {

	public static void main(String[] args) {
		int[] a = { 2, 4, 3, 1, 5 };
		select(a);
		System.out.println(Arrays.toString(a));
	}

	public static void select(int[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			int min = i; // 记录索引而不是值
			for (int j = i + 1; j < a.length; j++) {
				if (a[j] < a[min]) {
					min = j;
				}
			}
			int temp = a[i];
			a[i] = a[min];
			a[min] = temp;
		}
	}
}
