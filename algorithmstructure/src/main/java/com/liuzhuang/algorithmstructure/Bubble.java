package com.liuzhuang.algorithmstructure;

import java.util.Arrays;

/**
 * 冒泡排序 时间复杂度 O(N^2)
 * http://baike.baidu.com/item/%E5%86%92%E6%B3%A1%E6%8E%92%E5%BA%8F
 */
public class Bubble {

	// 基于样本测试的平均结果，不是极端结果
	private static int forCount = 0; // 总循环次数 [(1 + n) * n / 2] 首项加末项除以2
	private static int swapCount = 0; // 总交换次数 大体约为总循环次数 1/2

	public static void main(String[] args) {
		int count = 100;
		for (int i = 0; i < count; i++) {
			int[] a = Utils.generateArray(6);
			// a = new int[] { 6, 5, 4, 3, 2, 1 }; // 极端情况最差情况
			// a = new int[] { 1, 2, 3, 4, 5, 6 }; // 极端情况最好情况
			bubble(a);
			System.out.println(Arrays.toString(a));
		}
		System.out.println("forCount:" + forCount + " avg:" + forCount / Double.valueOf(count));
		System.out.println("swapCount:" + swapCount + " avg:" + swapCount / Double.valueOf(count));
	}

	/**
	 * 比如： <br>
	 * length = 6 <br>
	 * index = 0 1 2 3 4 5 <br>
	 * <br>
	 * 要比较 n-1 趟 <br>
	 */
	public static void bubble(int a[]) {
		for (int endIndex = a.length - 1; endIndex > 0; endIndex--) {

			for (int i = 0; i < endIndex; i++) {
				System.out.print(i + "-" + (i + 1) + " | ");
				if (a[i] > a[i + 1]) {
					int temp = a[i];
					a[i] = a[i + 1];
					a[i + 1] = temp;

					swapCount++;
				}
				forCount++;
			}
			System.out.println(".." + endIndex);
		}
	}
	
	public static void bubbleImpove(int a[]) {
		// 如果在一趟中都排好序了，就不用再循环了
		
		boolean isNeedSort = true;
		while (isNeedSort) {
			
			isNeedSort = false;
			for (int endIndex = a.length - 1; endIndex > 0; endIndex--) {

				for (int i = 0; i < endIndex; i++) {
					System.out.print(i + "-" + (i + 1) + " | ");
					if (a[i] > a[i + 1]) {
						int temp = a[i];
						a[i] = a[i + 1];
						a[i + 1] = temp;

						swapCount++;
						isNeedSort = true;
					}
					forCount++;
				}
				System.out.println(".." + endIndex);
			}
		}
	}
}
