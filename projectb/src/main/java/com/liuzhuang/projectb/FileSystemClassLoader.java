package com.liuzhuang.projectb;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 加载文件系统中的class文件
 */
public class FileSystemClassLoader extends ClassLoader {

  private String rootDir;

  public FileSystemClassLoader(String rootDir) {
    this.rootDir = rootDir;
  }

  @Override
  protected Class<?> findClass(String name) throws ClassNotFoundException {
    System.out.println("defineClass name : " + name);
    byte[] classData = loadClassData(name);
    return defineClass(name, classData, 0, classData.length);
  }

  private byte[] loadClassData(String name) {
    String path = classNameToPath(name);
    InputStream inputStream = null;
    try {
      inputStream = new FileInputStream(new File(path));
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      int bufferSize = 4096;
      byte[] buffer = new byte[bufferSize];
      int bytesNumRead = 0;
      while ((bytesNumRead = inputStream.read(buffer)) != -1) {
        byteArrayOutputStream.write(buffer, 0, bytesNumRead);
      }
      return byteArrayOutputStream.toByteArray();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return null;
  }

  private String classNameToPath(String name) {
    return rootDir + File.separatorChar + name.replace('.', File.separatorChar) + ".class";
  }
}
