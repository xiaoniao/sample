package com.liuzhuang.io;

// http://stackoverflow.com/questions/20966802/utf-16-character-encoding-of-java
// http://rosettacode.org/wiki/String_length#Java
import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * 字节流和字符流的区别
 * 
 * 提到字符,我们就会想到utf-8编码,一个汉字存储3个字节,blala.
 * 然后想汉字也可以用char表示,但是char在java规范中是2个字节,应该存不了汉字的吧,其实可以存用utf-16be存就能用2个字节表示.
 * 
 * java内部存储char的时候按utf-16be存储,我们取的时候可以在指定编码,比如utf-8,取出来就是3个字节,如果用utf-16be取就是2个字节
 */
public class ByteAndCharStream {

  /**
   * 问题1: char怎么保存成字节的<br>
   * char本来就是由两个字节组成,不存在转换,char是按utf-16be存储<br>
   * 问题2: 读取字符串怎么防止读到一半的问题<br>
   * char就是一个字符,一次读两个字节,读取之后再转换成相应的编码格式字节
   */
  public static void main(String[] args) throws IOException {
    innerCharset();
    charEncode();
    // showPlatformCharset();
    // showAllPlatformCharset();
    ext();
  }

  /**
   * java内部编码
   */
  private static void innerCharset() throws IOException {
    System.out.print("char 你:");
    CharArrayWriter charArrayWriter = new CharArrayWriter();
    charArrayWriter.write('你');
    for (char c : charArrayWriter.toCharArray()) {
      System.out.print(Integer.toHexString(c) + " ");
    }

    System.out.print("\n\"你\".getBytes(\"utf-16be\"):");
    
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("𝄪".getBytes("utf-16be"));
    int byteread;
    while ((byteread = byteArrayInputStream.read()) != -1) {
      System.out.print(Integer.toHexString(byteread) + " ");
    }

    System.out.println("");
  }

  /**
   * 误区:
   * 
   * "你好"是两个char,java规定一个char占2个字节,所以占4个字节.
   * 但是在utf-8下 "你好" 是占6个字节,那为什么会出现感觉一个char占3个字节的情况呢? 
   * 是因为编码不一致所以出现这种错觉,char是按utf-16存储所以占两个字节,而utf-8是不定长'你'会占3个字节
   * 
   * 
   * String.getBytes()是一个用于将String的内码转换为指定的外码的方法
   * https://www.zhihu.com/question/27562173
   */
  private static void charEncode() {
    String str = "编";
    try {
      System.out.println("你 utf-16be :" + str.getBytes("utf-16be").length + " 个字节"); // 2个字节(java内部默认编码)
      System.out.println("你 utf-16le :" + str.getBytes("utf-16le").length + " 个字节"); // 2个字节
      System.out.println("你 utf-16   :" + str.getBytes("utf-16").length + " 个字节"); // 4个字节
      System.out.println("你 utf-8    :" + str.getBytes("utf-8").length + " 个字节"); // 3个字节(常用编码)
    } catch (UnsupportedEncodingException e1) {
      e1.printStackTrace();
    }
  }

  // #编码集

  // 默认编码集
  private static void showPlatformCharset() {
    System.out.println("运行平台默认编码:" + Charset.defaultCharset().displayName());
  }

  // 支持的所有编码集
  private static void showAllPlatformCharset() {
    System.out.println("所有支持的编码集:");
    for (String charset : Charset.availableCharsets().keySet()) {
      System.out.println(charset);
    }
  }

  /**
   * http://mocha-c-163-com.iteye.com/blog/583064 UTF-8, UTF-16, UTF-16LE, UTF-16BE及其的BOM区别
   * 
   * UTF-16<br>2或4个字节
   * UTF-16BE<br>
   * UTF-16LE<br>
   * UTF-32<br>
   * UTF-32BE<br>
   * UTF-32LE<br>
   * UTF-8<br> 1-4个字节
   */
  
  // 占4个字节的字符无法用char表示，但是可以用字符串来表示2个code unit
  private static void ext() {
    String str = "\uD834\uDD2A"; //U+1D12A \uD834\uDD2A
    int not_really__the_length = str.length(); // value is 2, which is not the length in characters
    int actual_length = str.codePointCount(0, str.length()); // value is 1, which is the length in characters
    
    System.out.println(str);
    System.out.println(not_really__the_length);
    System.out.println(actual_length);
    //http://rosettacode.org/wiki/String_length#Java
  }
  
  // UTF-16编码方法 增补字符
  // http://www.cnblogs.com/maxupeng/archive/2011/06/22/2087579.html
  // 从 1.5 版开始，Java™ 语言就提供一些 API 来支持不能通过一个单一 16 位 char 数据类型表示的 Unicode 增补字符
  // https://www.ibm.com/developerworks/cn/java/j-unicode/
  
  //http://www.guokr.com/blog/83367/
  // 先处理高位再处理低位，叫做大端字节序；先处理低位再处理高位，叫做小端字节序。
  
  //JAVA几种常见的编码格式
  //http://www.blogjava.net/liuyz2006/articles/385768.html
  
  //符集与编码（一）——charset vs encoding
  //https://my.oschina.net/goldenshaw/blog/304493
  //字符集与编码（二）——编号 vs 编码
  //https://my.oschina.net/goldenshaw/blog/305805
  //字符集与编码（三）——定长与变长
  //https://my.oschina.net/goldenshaw/blog/307708
  //字符集与编码（四）——Unicode
  //https://my.oschina.net/goldenshaw/blog/310331
  //字符集与编码（五）——代码单元及length方法
  //https://my.oschina.net/goldenshaw/blog/311848
  //字符集与编码（六）——getBytes方法及乱码初步
  //https://my.oschina.net/goldenshaw/blog/313077
  //字符集与编码（七）——BOM
  //https://my.oschina.net/goldenshaw/blog/323248
  //字符集与编码（八）——ASCII和ISO-8859-1
  //https://my.oschina.net/goldenshaw/blog/351949
  //字符集与编码（九）——GB2312，GBK，GB18030
  //https://my.oschina.net/goldenshaw/blog/352859
}
