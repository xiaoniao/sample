package com.liuzhuang.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 什么是阻塞IO 代码执行不动直到有返回值,线程状态仍为runnable,而不是其他状态. (其实代码都是阻塞的) 什么是非阻塞IO
 */
public class FileCompare {

  public static void main(String[] args) {
    // 时间差不多
    readByBIO();
    readByNIO();
  }

  private static void readByBIO() {
    new Thread(() -> {
      long last = System.currentTimeMillis();
      try {
        byte[] buf = new byte[1024];
        FileInputStream fileInputStream = new FileInputStream("/Users/liuzhuang/Movies/Google&android/java/里美/aa.mp4");
        while (fileInputStream.read(buf) != -1) {

        }
        fileInputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      System.out.println("bio read over " + (System.currentTimeMillis() - last));
    }).start();
  }

  private static void readByNIO() {
    new Thread(() -> {
      long last = System.currentTimeMillis();
      try (
        FileInputStream fileInputStream = new FileInputStream("/Users/liuzhuang/Movies/Google&android/java/里美/aa.mp4");
        FileChannel fileChannel = fileInputStream.getChannel();
      ) {
        ByteBuffer buf = ByteBuffer.allocate(1024);
        while (fileChannel.read(buf) != -1) {
          buf.flip();
          buf.clear();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      System.out.println("nio read over " + (System.currentTimeMillis() - last));
    }).start();
  }
}
