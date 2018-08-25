package com.juvenxu.mvnbook.helloworld2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

/**
 * Hello world!
 *
 */
public class App {

	// list 初次扩容容量是10 扩容 至少 + 1/2 原20至少35 原40至少 60

	public static void main(String[] args) {
		System.out.println(1 + (1 >> 2));

		ArrayList<String> list = new ArrayList<String>();
		list.add("a");
		list.trimToSize();
		for (String string : list) {
			System.out.println(string);
		}
		System.out.println(list.size());

		int[] original = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
		int[] newer = Arrays.copyOf(original, 10);
		for (int i : newer) {
			System.out.println(i);
		}

		int[] src = { 1, 2, 3 };
		int[] dest = { 4, 5, 6, 7 };
		System.arraycopy(src, 0, dest, 0, 3);
		for (int i : src) {
			System.out.print(i);
		}
		System.out.println();
		for (int i : dest) {
			System.out.print(i);
		}

		Vector<String> vector = new Vector<String>();
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("", "");
	}
}
