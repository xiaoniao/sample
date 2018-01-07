package com.liuzhuang.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;


/**
 * char buf[] : 基于字符的IO流
 * 字符: 2个字节，utf-16be
 * 
 * Writer 写入时加锁Object
 *  -CharArrayWriter
 *  -OutputStreamWriter
 *  -FileWriter
 * 
 * Reader
 *  -CharArrayReader
 *  -InputStreamReader
 *  -FileReader
 */
public class CharIO {

  /**
   * writer 注意事项
   * 1.writer有1kb的缓存char数组(char[] writeBuffer), 作用是在write(int c)和write(String str)时避免重新new字节数组.
   * 2.从字符串中获得char字节通过 str.getChars(off, (off + len), cbuf, 0) 
   */
  public void write() throws IOException {
    
    // FilterWriter 装饰器模式
    
    // 字符数组
    CharArrayWriter charArrayWriter = new CharArrayWriter();
    charArrayWriter.write("你好"); // str.getChars(off, off + len, buf, count);
    charArrayWriter.write("hello");
    System.out.println(charArrayWriter.toString());;
    
    // 写缓存,先写入缓存,超过大小后,再实际写入流
    BufferedWriter bufferedWriter = new BufferedWriter(charArrayWriter);
    bufferedWriter.write("你好");
    System.out.println(charArrayWriter.toString());
    bufferedWriter.flush(); // flush可强制刷新到实际流中
    System.out.println(charArrayWriter.toString());
    
    // 连接字节流转换为字符流
    try {
      OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new ByteArrayOutputStream(), "utf-8");
      outputStreamWriter.write("你好");
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    // 写文件
    try {
      FileWriter fileWriter = new FileWriter("test2");
      fileWriter.write("你好");
      fileWriter.write(109990);
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    // 打印字节流
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    PrintWriter streamPrintWriter = new PrintWriter(byteArrayOutputStream);
    streamPrintWriter.write("hello");
    streamPrintWriter.flush();
    System.out.println(byteArrayOutputStream.toString());
    
    // 打印字符流
    PrintWriter printWriter = new PrintWriter(charArrayWriter);
    printWriter.write("你好");
    System.out.println(charArrayWriter.toString());
    
    // 字符串
    StringWriter stringWriter = new StringWriter();
    stringWriter.write("你好");
    
    // PipedWriter
  }
  
  public void read() throws IOException {
    
    // char数组
    System.out.println("#char数组");
    char[] chars = {'你', '好', '\r', '世', '界'};
    CharArrayReader charArrayReader = new CharArrayReader(chars);
    System.out.println((char)charArrayReader.read());
    System.out.println((char)charArrayReader.read());
    
    // FilterReader 装饰器模式类基类
    
    // 连接字节流转为字符流 InputStreamReader
    System.out.println("#InputStreamReader");
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("你好".getBytes());
    InputStreamReader inputStreamReader = new InputStreamReader(byteArrayInputStream);
    //System.out.println((char)inputStreamReader.read());
    //System.out.println((char)inputStreamReader.read());
    
    // 缓冲字符数组,默认8k的缓冲区,可一次读一行
    System.out.println("#缓冲字符数组");
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
    //System.out.println((char)bufferedReader.read());
    System.out.println(bufferedReader.readLine());
    
    // 连接文件, 但是对于字节是4的字符,需要用2个char读取
    System.out.println("#读取文件");
    inputStreamReader = new InputStreamReader(new FileInputStream("test"));
    char[] data = new char[1];//2
    inputStreamReader.read(data);
    inputStreamReader.close();
    System.out.println(new String(data));
    
    // FileReader继承自InputStreamReader 等同上面
    System.out.println("#读取文件2");
    FileReader fileReader = new FileReader("test");
    System.out.println((char)fileReader.read());
    fileReader.close();
    
    special();
  }
  
  /**
   * 特例字符
   * @throws IOException 
   */
  private void special() throws IOException {
    System.out.println("#special");
    String specialChar = "𝄪"; // 字符占4个字节 增补字符
    InputStreamReader inputStreamReader = new InputStreamReader(new ByteArrayInputStream(specialChar.getBytes()));
    char[] data = new char[2];
    inputStreamReader.read(data);
    inputStreamReader.close();
    System.out.println(new String(data));
  }
}
