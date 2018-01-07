package com.liuzhuang.algorithmstructure;

public class AA {

	public static void main(String[] args) {
		// 如果发展下线层级到20层
		long people = level(10);
		System.out.println(people / 100000000 + "亿人");
		System.out.println("总钱数" + people * 5000 / 100000000 + "亿");
	}

	public static long level(int level) {
		long count = 0;
		while (level > 0) {
			count += pow(level - 1);
			level--;
		}
		return count;
	}

	public static long pow(int pow) {
		long result = 1;
		while (pow > 0) {
			result *= 10;
			pow--;
		}
		return result;
	}
}
