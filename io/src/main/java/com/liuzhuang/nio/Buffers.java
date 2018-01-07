package com.liuzhuang.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

/**
 * 详解各种buffer的使用
 * 每种基本类型都对应一个buffer
 */
public class Buffers {

  public static void main(String[] args) throws IOException {
    byteBuffer();
    charBuffer();
    shortBuffer();
    intBuffer();
    longBuffer();
    floatBuffer();
    doubleBuffer();
  }

  /**
   * byte[]
   */
  private static void byteBuffer() throws IOException {
    System.out.println(":byteBuffer");
    ByteBuffer buffer = ByteBuffer.allocate(10);
    buffer.put("你好".getBytes("utf-16be"));
    System.out.println(buffer);
    buffer.flip();// 切换到读模式
    System.out.println(buffer);
    while (buffer.hasRemaining()) {
      System.out.println(buffer.getChar());
    }
  }

  /**
   * char[]
   */
  private static void charBuffer() {
    System.out.println(":charBuffer");
    CharBuffer charBuffer = CharBuffer.allocate(2);
    charBuffer.put('你');
    charBuffer.put('好');
    charBuffer.flip();
    System.out.println(charBuffer.get());
    System.out.println(charBuffer.get());
  }

  /**
   * short[]
   */
  private static void shortBuffer() {
    System.out.println(":shortBuffer");
    ShortBuffer shortBuffer = ShortBuffer.allocate(2);
    shortBuffer.put((short) 1);
    shortBuffer.put((short) 2);
    shortBuffer.flip();
    System.out.println(shortBuffer.get());
    System.out.println(shortBuffer.get());
  }

  /**
   * int[]
   */
  private static void intBuffer() {
    System.out.println(":intBuffer");
    IntBuffer intBuffer = IntBuffer.allocate(2);
    intBuffer.put(1);
    intBuffer.put(2);
    intBuffer.flip();
    System.out.println(intBuffer.get());
    System.out.println(intBuffer.get());
  }

  /**
   * long[]
   */
  private static void longBuffer() {
    System.out.println(":longBuffer");
    LongBuffer longBuffer = LongBuffer.allocate(2);
    longBuffer.put(1);
    longBuffer.put(2);
    longBuffer.flip();
    System.out.println(longBuffer.get());
    System.out.println(longBuffer.get());
  }

  /**
   * float[]
   */
  private static void floatBuffer() {
    System.out.println(":floatBuffer");
    FloatBuffer floatBuffer = FloatBuffer.allocate(2);
    floatBuffer.put(1.0f);
    floatBuffer.put(2.0f);
    floatBuffer.flip();
    System.out.println(floatBuffer.get());
    System.out.println(floatBuffer.get());
  }

  /**
   * double[]
   */
  private static void doubleBuffer() {
    System.out.println(":doubleBuffer");
    DoubleBuffer doubleBuffer = DoubleBuffer.allocate(2);
    doubleBuffer.put(1.0);
    doubleBuffer.put(2.0);
    doubleBuffer.flip();
    System.out.println(doubleBuffer.get());
    System.out.println(doubleBuffer.get());
  }
}
