package com.liuzhuang.string;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Filessss {

	public static String getRandomString(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		File file = new File("D:\\ffff\\sss.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fileWriter = new FileWriter(file);
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			fileWriter.write(getRandomString(6));
			fileWriter.write("\r\n");
		}
		fileWriter.flush();
		fileWriter.close();
		System.out.println("over");
	}

}
