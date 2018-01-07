package com.liuzhuang.algorithmstructure;

import java.util.Random;

public class Utils {

	/**
	 * 随机生成长度为length的纯数字数组
	 */
	public static int[] generateArray(int length) {
		int[] result = new int[length];
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			result[i] = random.nextInt(10000);
		}
		return result;
	}
}
