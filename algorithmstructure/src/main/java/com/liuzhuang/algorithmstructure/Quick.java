package com.liuzhuang.algorithmstructure;

import java.util.Arrays;

public class Quick {

	public static void sort(Comparable<?>[] a) {
		sort(a, 0, a.length - 1);
	}

	/**
	 * 快速排序从a[lo]到a[hi]的子集
	 */
	private static void sort(Comparable<?>[] a, int lo, int hi) {
		System.out.println(Arrays.toString(a));
		if (hi <= lo)
			return;

		int j = partition(a, lo, hi);
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}

	/**
	 * partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
	 * and return the index j.
	 */
	private static int partition(Comparable<?>[] a, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		Comparable<?> v = a[lo];
		while (true) {

			// find item on lo to swap
			while (less(a[++i], v))
				if (i == hi)
					break;

			// find item on hi to swap
			while (less(v, a[--j]))
				if (j == lo)
					break; // redundant since a[lo] acts as sentinel

			// check if pointers cross
			if (i >= j)
				break;

			exch(a, i, j);
		}

		// put partitioning item v at a[j]
		exch(a, lo, j);

		// now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
		return j;
	}

	/**
	 * Rearranges the array so that {@code a[k]} contains the kth smallest key;
	 * {@code a[0]} through {@code a[k-1]} are less than (or equal to)
	 * {@code a[k]}; and {@code a[k+1]} through {@code a[n-1]} are greater than
	 * (or equal to) {@code a[k]}.
	 *
	 * @param a
	 *            the array
	 * @param k
	 *            the rank of the key
	 * @return the key of rank {@code k}
	 */
	public static Comparable select(Comparable[] a, int k) {
		if (k < 0 || k >= a.length) {
			throw new IndexOutOfBoundsException("Selected element out of bounds");
		}
		int lo = 0, hi = a.length - 1;
		while (hi > lo) {
			int i = partition(a, lo, hi);
			if (i > k)
				hi = i - 1;
			else if (i < k)
				lo = i + 1;
			else
				return a[i];
		}
		return a[lo];
	}

	/***************************************************************************
	 * Helper sorting functions.
	 ***************************************************************************/

	// is v < w ?
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	/**
	 * 交换数组中元素位置
	 */
	private static void exch(Object[] a, int i, int j) {
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	/**
	 * 打印数组结果
	 */
	private static void show(Comparable<?>[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}

	public static void main(String[] args) {
		String[] a = { "a", "v", "b" };
		Quick.sort(a);
		show(a);

		// display results again using select
		for (int i = 0; i < a.length; i++) {
			String ith = (String) Quick.select(a, i);
			System.out.println(ith);
		}
	}
}
