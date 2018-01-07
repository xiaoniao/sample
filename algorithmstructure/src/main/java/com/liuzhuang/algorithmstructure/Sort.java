package com.liuzhuang.algorithmstructure;

import java.util.Arrays;
import java.util.Random;

/**
 * 排序算法
 */
public class Sort {

	public static void main(String[] args) {
		// sort();
		// barrel();
		bubbling();
	}

	public static void sort() {
		Random random = new Random();
		for (int k = 0; k < 100; k++) {
			int[] nums = { random.nextInt(100), random.nextInt(100), random.nextInt(100), random.nextInt(100),
					random.nextInt(100), random.nextInt(100) };

			int swapCount = 0;
			for (int i = 0; i < nums.length - 1; i++) {
				int max = nums[i];
				for (int j = i + 1; j < nums.length; j++) {
					if (max < nums[j]) {
						// 交换元素位置
						int temp = nums[i];
						nums[i] = nums[j];
						nums[j] = temp;
						max = nums[i];
						swapCount++;
					}
				}
			}
			System.out.println(Arrays.toString(nums) + " swapCount:" + swapCount);
		}
	}

	/**
	 * 桶排序 先把12345678排好序，然后填坑。 缺点分配空间大
	 */
	public static void barrel() {
		Random random = new Random();
		for (int k = 0; k < 100; k++) {
			int[] nums = { random.nextInt(100), random.nextInt(100), random.nextInt(100), random.nextInt(100),
					random.nextInt(100), random.nextInt(100) };

			int[] placeHolder = new int[100];
			for (int i = 0; i < nums.length; i++) {
				placeHolder[nums[i]]++;
			}

			for (int value = placeHolder.length - 1; value >= 0; value--) {
				int count = placeHolder[value];
				for (int j = 0; j < count; j++) {
					System.out.print(value + " ");
				}
			}
			System.out.println("");
		}
	}

	/**
	 * 冒泡排序
	 */
	public static void bubbling() {
		int[] nums = { 12, 35, 99, 18, 76 };
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums.length - (i + 1); j++) {
				System.out.print(j + "-" + (j + 1) + " ");
				if (nums[j] < nums[j + 1]) {
					int t = nums[j];
					nums[j] = nums[j + 1];
					nums[j + 1] = t;
				}
			}
			System.out.println();
		}
		System.out.println(Arrays.toString(nums));
	}
}
