package com.liuzhuang.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 我们以读文件的方式来证明按字节读和按块读的性能差距有多大,所以为什么NIO要以块为单位.
 */
public class Cache {
  
  public static void main(String[] args) {
    new Thread(() -> {
      long last = System.currentTimeMillis();
      try {
        byte[] buf = new byte[1024];
        FileInputStream fileInputStream = new FileInputStream("/Users/liuzhuang/Movies/Google&android/java/里美/aa.mp4");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        bufferedInputStream.read();
        while (fileInputStream.read(buf) != -1) {
          //read(buf)对应的native方法 private native int readBytes(byte b[], int off, int len) throws IOException;
          //System.out.println(new String(buf));
        }
        fileInputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      System.out.println("read over " + (System.currentTimeMillis() - last) );
    }).start();
  }
  
  //文件大小 10.03G
  //1b   1   时间太长   1~2M/s
  //1kb 1024 15.361s  668M/s
  //2kb 2048 12.845s  799M/s
  //3kb 3072 12.408s  827M/s
  //4kb 4096 11.829s  868M/s
  //1M       11.462s 
}
