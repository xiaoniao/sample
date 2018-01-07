package com.liuzhuang.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Channelmm {

  public static void main(String[] args) {
    fileChannel();
  }
  
  /**
   * 读写文件
   * 
   * flip - 轻抛,开关 - 切换读写模式 区别在于 读的时候limit为实际大小, 写的时候limit是容量大小.
   * FileChannel不适合读取文本文件
   */
  private static void fileChannel() {
    try (
      FileInputStream fileInputStream = new FileInputStream("test");
      FileChannel fileChannel = fileInputStream.getChannel();
    ) {
      ByteBuffer buf = ByteBuffer.allocate(4); // 因为UTF-8是变长保存,所以4个字节不能保证读到一个完整的code point(字符),所以需要特定的编码集来解析字节
      while (fileChannel.read(buf) != -1) {
        buf.flip();
        while (buf.hasRemaining()) {
          byte[] data = new byte[4];
          buf.get(data);
          System.out.println(new String(data));
        }
        buf.clear();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
