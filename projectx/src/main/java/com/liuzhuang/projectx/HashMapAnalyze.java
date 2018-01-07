package com.liuzhuang.projectx;

import java.util.HashMap;

public class HashMapAnalyze {

	public static void main(String[] args) {
		HashMap<String, String> hashMap = new HashMap<>();
		for (int i = 0; i < 10000; i++) {
			hashMap.put(String.valueOf(i), String.valueOf(i));
		}
		hashMap.forEach((String key, String value) -> {
			System.out.println(key + ":" + value);
		});
		System.out.println("size:" + hashMap.size());
	}
}
