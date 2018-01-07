package com.jakewharton.disklrucache;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import com.jakewharton.disklrucache.DiskLruCache.Editor;
import com.jakewharton.disklrucache.DiskLruCache.Snapshot;

public class Mine {
	
	public static String getRandomString(int length) { //length表示生成字符串的长度  
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {
	        int number = random.nextInt(base.length());
	        sb.append(base.charAt(number));
	    }
	    return sb.toString();
	 }
	
	public static void main(String[] args) throws IOException {
//		File text1 = new File("D:\\file\\cache\\text1.txt");
//		File text2 = new File("D:\\file\\cache\\text2.txt");
//		System.out.println(text1.renameTo(text2));;
		
		File directory = new File("D:\\file\\cache");
		int appVersion = 1024;
		int valueCount = 3;
		int maxSize = Integer.MAX_VALUE;
		DiskLruCache diskLruCache = DiskLruCache.open(directory, appVersion, valueCount, maxSize);
		
		Editor editor = diskLruCache.edit("test");
		editor.set(0, "bb");
		editor.set(1, "bbbb");
		editor.set(2, "bbbbbb");
		
		for (String string : directory.list()) {
			System.out.println(string);
		}
		System.out.println("-------------------");
		editor.commit();
		for (String string : directory.list()) {
			System.out.println(string);
		}
		
		Snapshot snapshot = diskLruCache.get("test");
		System.out.println(snapshot.getString(0));
		System.out.println(snapshot.getString(1));
		System.out.println(snapshot.getString(2));
		
//		Snapshot snapshot = diskLruCache.get("user");
//		System.out.println(snapshot.getString(0));
//		System.out.println(snapshot.getString(1));
//		System.out.println(snapshot.getString(2));
//		
//		for (int i = 0; i < 1; i++) {
//			Editor editor = diskLruCache.edit(getRandomString(10));
//			editor.set(0, getRandomString(999));
//			editor.set(1, getRandomString(999));
//			editor.set(2, getRandomString(999));
//			editor.commit();
//		}
//		diskLruCache.test();
//		diskLruCache.get("").getString(0);
		diskLruCache.close();
	}
}
