package com.liuzhuang.algorithmstructure;

public class Math {

	public static void main(String[] args) {
		abs(-10);
		abs(-10.1);
		System.out.println(sqrt(81));
		;
		hypotenuse();
		H();
	}

	// 计算整数绝对值
	public static void abs(int x) {
		if (x < 0) {
			x = -x;
		}
		System.out.println("abs:" + x);
	}

	// 计算浮点数的绝对值
	public static void abs(double x) {
		if (x < 0.0) {
			x = -x;
		}
		System.out.println("abs:" + x);
	}

	// 计算平方根
	// 牛顿法 http://www.cnblogs.com/xkfz007/archive/2012/05/15/2502348.html
	// http://www.cnblogs.com/vagerent/archive/2007/06/25/794695.html
	public static double sqrt(double c) {
		if (c < 0) {
			return Double.NaN;
		}
		double err = 1e-15;
		double t = c;
		while (java.lang.Math.abs(t - c / t) > (err * t)) {
			t = (c / t + t) / 2.0;
		}
		return t;
	}

	// 计算直角三角形的斜边
	public static void hypotenuse() {

	}

	// 计算调和函数
	public static void H() {

	}
}
