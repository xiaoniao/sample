package com.juvenxu.mvnbook.helloworld.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

public class Anylas {

	/**
	 * 词义分析
	 */
	public static String read() {
		StringBuffer stringBuffer = new StringBuffer();
		File file = new File("/Users/liuzhuang/Desktop/daguguji2/all" + ".txt");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				stringBuffer.append(tempString.split(" : ")[1]);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return stringBuffer.toString();
	}

	static Map<String, Integer> map = new HashMap<String, Integer>();

	public static void main(String[] args) {

		Result result = ToAnalysis.parse(read());
		for (Term term : result) {
			String name = term.getName();
			if (map.containsKey(name)) {
				map.put(name, map.get(name) + 1);
			} else {
				map.put(name, 1);
			}
		}
		
		List<String> list = new ArrayList<String>();
		for (String key : map.keySet()) {
			list.add(key + "divi999999der" + map.get(key));
		}
		
		System.out.println("---v1");
		System.out.println(list.get(0));
		System.out.println(list.get(list.size() - 1));
		
		Collections.sort(list, new Comparator<String>() {

			public int compare(String o1, String o2) {
				int v1 = Integer.valueOf(o1.split("divi999999der")[1]);
				int v2 = Integer.valueOf(o2.split("divi999999der")[1]);
				
//				System.out.println(v1 + "----" + v2);
				return v2 - v1;
			}
		});
		
		StringBuffer stringBuffer = new StringBuffer();
		for (String string : list) {
			int count = Integer.valueOf(string.split("divi999999der")[1]);
			string = string.replaceAll("\\w", "");
			string = string.replaceAll("\\s", "");
//			string = string.replaceAll("divi999999der", " - ");
			
			if (string.length() > 0 && !string.equals(" ")) {
				stringBuffer.append(string + appendWhite(string) + count + "\r\n");
			}
			
		}
		save(stringBuffer.toString());
		System.out.println("over");
	}
	
	private static String appendWhite(String content) {
		StringBuffer stringBuffer = new StringBuffer();
		int n = 20 - content.length();
		while(n > 0) {
			stringBuffer.append(" ");
			n--;
		}
		return stringBuffer.toString();
	}
	
	public static void save(String content) {
		
		File file = new File("/Users/liuzhuang/Desktop/daguguji2/all_list" + ".txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(file);
			fileWriter.write(content);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
