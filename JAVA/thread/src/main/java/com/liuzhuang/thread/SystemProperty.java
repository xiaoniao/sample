package com.liuzhuang.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * 获得系统属性
 */
public class SystemProperty {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		Properties properties = System.getProperties();
		for (Object key : properties.keySet()) {
			list.add(key + appendWhiet(String.valueOf(key)) + ">   " + properties.getProperty((String) key));
		}
		Collections.sort(list);
		for (String string : list) {
			System.out.println(string);
		}
		
		System.out.print("CPU个数:");
		System.out.println(Runtime.getRuntime().availableProcessors());
		System.out.print("虚拟机内存总量:");
		System.out.println(Runtime.getRuntime().totalMemory() / 1024 / 1024);
		System.out.print("虚拟机空闲内存量:");
		System.out.println(Runtime.getRuntime().freeMemory()  / 1024 / 1024);
		System.out.print("虚拟机使用最大内存量:");
		System.out.println(Runtime.getRuntime().maxMemory()  / 1024 / 1024);
	}

	private static String appendWhiet(String key) {
		StringBuffer stringBuffer = new StringBuffer();
		int whitespace = 33 - key.length();
		while (whitespace > 0) {
			stringBuffer.append(" ");
			whitespace--;
		}
		return stringBuffer.toString();
	}
}
