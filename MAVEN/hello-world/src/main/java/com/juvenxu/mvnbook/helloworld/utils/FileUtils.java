package com.juvenxu.mvnbook.helloworld.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

	public static String read(int page) {
		StringBuffer stringBuffer = new StringBuffer();
		File file = new File("/Users/liuzhuang/Desktop/daguguji2/daguguji" + page + ".txt");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				stringBuffer.append(tempString);
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

	public static void save(String content, int page) {

		File file = new File("/Users/liuzhuang/Desktop/daguguji2/liuzhuang" + page + ".txt");
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

	public static void save(String content, String fileName) {

		File file = new File("/Users/liuzhuang/Desktop/daguguji2/" + fileName + ".txt");
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


	public static String read(String fileName) {
		StringBuffer stringBuffer = new StringBuffer();
		File file = new File("/Users/liuzhuang/Desktop/daguguji2/" + fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				stringBuffer.append(tempString);
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
	
	///////

	public static void save2(String result, String dir, String fileName) {
		File dirFile = new File(dir);
		File file = new File(dir + "/" + fileName);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(file);
			fileWriter.write(result);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String read2(String fileName) {
		StringBuffer stringBuffer = new StringBuffer();
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				stringBuffer.append(tempString);
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
}
