package com.liuzhuang.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PushbackInputStream;
import java.io.SequenceInputStream;
import java.lang.reflect.Field;

/**
 * byte buf[]
 * 字节流 基于InputStream读取流和OutputStream写入流
 * 
 * http://docs.oracle.com/javase/7/docs/api/java/io/InputStream.html
 * http://docs.oracle.com/javase/7/docs/api/java/io/OutputStream.html
 * 装饰器模式
 * 
 * 线程安全
 * 
 */
public class ByteIO {

  /**
   * 字节输出流 写入
   * 
   * OutputStream 输出流的抽象类,定义了输出流格式,超类,其子类要实现writ方法并写入字节<br>
   * 
   * 实际流
   * ByteArrayOutputStream 写入字节数组
   * FileOutputStream 写入文件
   * PipedOutputStream 写入管道
   * 
   * 装饰流 锦上添花
   * FilterOutputStream 方便增加代理
   * BufferedOutputStream 缓冲数组
   * DataOutputStream 基本数据类型
   * ObjectOutputStream 对象数据类型序列化保存
   */
  @SuppressWarnings("resource")
  public void write(String value) throws IOException, InterruptedException {
    
    // FilterOutputStream是装饰器模式的父类,封装了代理类. 例如BufferedOutputStream
    
    // 默认字节数组长度为32,长度不够时扩增至之前一倍.
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    outputStream.write(value.getBytes());
    outputStream.toByteArray();
    outputStream.close();

    // 增加了缓冲字节数组,默认大小是8kb(8192).写入数据时判断缓冲数组是否已满,满的话会把缓冲字节数组全部写入代理的数据流中,分支结束,
    // 最后加入缓冲字节数组,可手动调用flush把缓冲字节数组数据全部写入代理的输出流
    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
    bufferedOutputStream.write(1);
    bufferedOutputStream.close();

    // 扩展到基本java类型,在内部把java类型转换成字节再写入.
    DataOutputStream dataOutputStream = new DataOutputStream(bufferedOutputStream);
    dataOutputStream.writeLong(1L);
    dataOutputStream.writeBoolean(true);
    dataOutputStream.writeUTF("hello");
    dataOutputStream.close();
    
    // 扩展到所有的java类型但是需要实现Serializable接口,在内部把java类型转换成字节再写入. [序列化保存对象]
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(dataOutputStream);
    objectOutputStream.writeObject(new Model());
    objectOutputStream.writeLong(1L);
    objectOutputStream.close();
    
    // FileOutputStream 文件写入
    File file = new File("test");
    if (!file.exists()) {
      file.createNewFile();
    }
    FileOutputStream fileOutputStream = new FileOutputStream(file);
    DataOutputStream fileDataOutputStream = new DataOutputStream(fileOutputStream);//加入一层DataOutputStream,方便写入字符串
    fileDataOutputStream.writeUTF("你好");
    fileDataOutputStream.close();
    
    // 管道输出流
    PipedOutputStream pipedOutputStream = new PipedOutputStream();
    PipedInputStream pipedInputStream = new PipedInputStream(pipedOutputStream);
    new Thread(() -> {
      try {
        while (true) {
          pipedOutputStream.write(18);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }).start();
    new Thread(() -> {
      try {
        while (true) {
          int result = pipedInputStream.read();
          System.out.println(result);
        }
        //pipedInputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }).start();
  }

  /**
   * 字节输入流 InputStream
   * 
   * InputStream 输入流超类,定义了读行为.
   * 
   * 实际流
   * ByteArrayInputStream 读取数组字节
   * FileInputStream 文件
   * 
   * 装饰流
   * BufferedInputStream 读缓冲 预先读1kb数据
   * DataInputStream java基本类型
   * ObjectInputStream java对象
   * SequenceInputStream 顺序读多个流
   * PushbackInputStream 允许回退修改数据流
   */
  public void read() throws IOException {
    
    // FilterInputStream 封装了装饰器模式
    
    // ByteArrayInputStream读取字节数组
    byte[] bs = new byte[8194];
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bs);
    
    // 增加缓冲数组 缓冲大小默认8kb 1.增加提前缓存功能 先一次性读取8kb大小的数据,然后每次的读取是从缓存中读,能降低直接读取io的次数 2.支持mark和reset操作
    BufferedInputStream bufferedInputStream = new BufferedInputStream(byteArrayInputStream);
    int byteread = 0;
    printInputBufferInfo(bufferedInputStream);
    while ((byteread = bufferedInputStream.read()) != -1) {
      //printInputBufferInfo(bufferedInputStream);
      //System.out.print(byteread + " ");
    }
    
    // 读基本类型 线程不安全 似乎只能和DataOutputStream配合使用,详细的之后再看
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
    dataOutputStream.writeUTF("你好");
    DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
    System.out.println(dataInputStream.readUTF());
    
    // FileInputStream 读取文件 只用字节读取会出现一个汉字分为两部分字节的问题 所以有了基于字符的流
    FileInputStream fileInputStream = new FileInputStream("test");
    BufferedInputStream fileBufferedInputStream = new BufferedInputStream(fileInputStream);
    byte[] data = new byte[10];
    while (fileBufferedInputStream.read(data) != -1) {
      System.out.println(new String(data));
    }
    fileBufferedInputStream.close();
    
    // ObjectInputStream 要结合ObjectOutputStream使用 
    ByteArrayOutputStream objectArrayOutputStream = new ByteArrayOutputStream();
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(objectArrayOutputStream);
    objectOutputStream.writeObject(new Model());
    ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(objectArrayOutputStream.toByteArray()));
    try {
      System.out.println(objectInputStream.readObject());
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    };
    
    // PipedInputStream参见本文write方法
    
    // SequenceInputStream 顺序读两个字节流
    ByteArrayInputStream s1 = new ByteArrayInputStream("你".getBytes());
    ByteArrayInputStream s2 = new ByteArrayInputStream("好".getBytes());
    SequenceInputStream sequenceInputStream = new SequenceInputStream(s1, s2);
    
    int index = 0;
    byte[] combine = new byte[100];
    int byteRead;
    while ((byteRead = sequenceInputStream.read()) != -1) {
      System.out.print(byteRead + "");
      combine[index] = (byte) byteRead;
      index++;
    }
    System.out.println(new String(combine));
    
    // PushbackInputStream
    PushbackInputStream pushbackInputStream = new PushbackInputStream(new ByteArrayInputStream("hello".getBytes()));
    while ((byteRead = pushbackInputStream.read()) != -1) {
      char letter = (char) byteRead;
      if (letter == 'e') {
        pushbackInputStream.unread('X'); //回退并修改上一个自己的值
        System.out.println("change: " + letter + " to X");
      } else {
        System.out.println("value: " + letter);
      }
    }
  }
  
  /**
   * 打印缓存输入流内部信息
   */
  private void printInputBufferInfo(BufferedInputStream bufferedInputStream) {
    Class<?> cls = bufferedInputStream.getClass();
    try {
      
      Field posfield = cls.getDeclaredField("pos");
      posfield.setAccessible(true);
      System.out.print("pos:" + posfield.get(bufferedInputStream));
      
      Field field = cls.getDeclaredField("count");
      field.setAccessible(true);
      System.out.print(" count:" + field.get(bufferedInputStream));
      
      Field buffield = cls.getDeclaredField("buf");
      buffield.setAccessible(true);
      byte[] buf = (byte[]) buffield.get(bufferedInputStream);
      System.out.println(" buf:" + buf.length);
      
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
      e.printStackTrace();
    }
  }
}
