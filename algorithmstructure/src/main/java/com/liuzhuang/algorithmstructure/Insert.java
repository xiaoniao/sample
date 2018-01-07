package com.liuzhuang.algorithmstructure;

import java.util.Arrays;

/**
 * 插入排序 玩扑克牌就是插入排序
 */
public class Insert {

	// 基于样本测试的平均结果，不是极端结果
	private static int forCount = 0; // 总循环次数
	private static int swapCount = 0; // 总交换次数

	public static void main(String[] args) {
		int count = 100;
		for (int i = 0; i < count; i++) {
			int[] a = Utils.generateArray(6);
			insert(a);
			System.out.println(Arrays.toString(a));
		}
		System.out.println("forCount:" + forCount + " avg:" + forCount / Double.valueOf(count));
		System.out.println("swapCount:" + swapCount + " avg:" + swapCount / Double.valueOf(count));
	}

	/**
	 * 批量往后移 和一次一次往前移 一样
	 */
	private static int[] insert(int[] a) {
		if (a == null || a.length < 2) {
			return a;
		}

		for (int i = 1; i < a.length; i++) {
			for (int j = i; j > 0; j--) {
				forCount++;
				
				if (a[j] < a[j - 1]) {
					int temp = a[j];
					a[j] = a[j - 1];
					a[j - 1] = temp;
					swapCount++;
					
				} else {
					break;
				}
			}
		}
		return a;
	}
}
