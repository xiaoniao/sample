package com.liuzhuang.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * .nio 字符集
 */
public class Charsets {

  public static void main(String[] args) throws CharacterCodingException {
    
    Charset charset = Charset.defaultCharset();
    // charset = Charset.forName("UTF-16BE");
    System.out.println("charset:" + charset);
    
    /**
     * 字符 转 字节
     */
    CharsetEncoder charsetEncoder = charset.newEncoder();
    CharBuffer charBuffer = CharBuffer.allocate(1024);
    charBuffer.put('你');
    charBuffer.put('好');
    charBuffer.flip();//切换模式
    ByteBuffer byteBuffer = charsetEncoder.encode(charBuffer);
    
    /**
     * 字节 转 字符
     */
    CharsetDecoder charsetDecoder = charset.newDecoder();
    CharBuffer data = charsetDecoder.decode(byteBuffer);
    System.out.println(data.toString());
    
    printHex(data);
  }
  
  private static void printHex(CharBuffer data) {
    for (char c : data.array()) {
      System.out.print(Integer.toHexString(c) + " ");
    }
  }
}
