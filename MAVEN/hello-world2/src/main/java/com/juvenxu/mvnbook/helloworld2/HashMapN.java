package com.juvenxu.mvnbook.helloworld2;

import java.util.HashMap;

public class HashMapN {

	public static void main(String[] args) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("a", "");
		map.get("a");
		
		map.put(null, "111");
		System.out.println(map.get(null));
	}
}
