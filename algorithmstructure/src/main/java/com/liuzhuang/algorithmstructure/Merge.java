package com.liuzhuang.algorithmstructure;

import java.util.Arrays;

/**
 * 归并排序
 * http://baike.baidu.com/item/%E5%BD%92%E5%B9%B6%E6%8E%92%E5%BA%8F?sefr=cr
 */
public class Merge {

	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 4 };
		int[] b = { 4, 5, 6, 7 };
		int[] c = new int[a.length + b.length];
		memeryArray(a, b, c);
		System.out.println(Arrays.toString(c));
	}

	public static void memeryArray(int a[], int b[], int c[]) {
		int n = a.length;
		int m = b.length;

		int i, j, k;
		i = j = k = 0;

		while (i < n && j < m) {
			if (a[i] < b[j])
				c[k++] = a[i++];
			else
				c[k++] = b[j++];
		}

		while (i < n)
			c[k++] = a[i++];

		while (j < m)
			c[k++] = b[j++];
	}
}
