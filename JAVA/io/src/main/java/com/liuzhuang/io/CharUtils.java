package com.liuzhuang.io;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * char buf[]
 * 基于字符的IO Writer 和 Reader
 * 
 * 问题1: char怎么保存成字节的
 *        char本来就是由两个字节组成,不存在转换,默认按utf-16be存储
 *         -----
 *          char 和 byte 本质上是同一类东西 字节 char长度为2 byte长度为1 ,保存的都是数字,所以不存在转换一说,它们的却别在于它们的解释器怎么执行.
 *         -----
 * 问题2: 读取字符串怎么防止读到一半的问题
 */
public class CharUtils {

  /**
   * 问题2:
   * 复写PrintStream
   */
  public void reloadOut() {
    System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)) {
      @Override
      public void println(String x) {
        System.out.print("*****\n");
        super.println(x);
        System.out.print("*****\n");
      }
    });
    System.out.println("hello");
  }
}
