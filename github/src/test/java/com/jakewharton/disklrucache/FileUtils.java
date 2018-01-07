package com.jakewharton.disklrucache;

import java.io.File;

public class FileUtils {

	public static void deleteDirectory(File file) {
		if (!file.isDirectory()) {
			file.delete();
		} else {
			if (file.list().length > 0) {
				for (File f : file.listFiles()) {
					deleteDirectory(f);
				}
			}
		}
	}

}
