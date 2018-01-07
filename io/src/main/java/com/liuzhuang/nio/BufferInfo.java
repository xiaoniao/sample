package com.liuzhuang.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * position
 * limit
 * capacity
 */
public class BufferInfo {

  public static void main(String[] args) {
    BufferInfo nio = new BufferInfo();
    nio.testPutChar();
  }
  
  private void testPutChar() {
    ByteBuffer buffer = ByteBuffer.allocate(10);
    buffer.putChar('你');
    buffer.putChar('好');
    buffer.flip(); // 切换写模式到读模式
    System.out.println("实际占用字节:" + buffer.limit());
    char c1 = buffer.getChar();
    char c2 = buffer.getChar();
    System.out.println(c1 + "" + c2);
    
    for (byte b : buffer.array()) {
      System.out.print(Integer.toHexString(b) + " ");
    }
  }
  
  /**
   * ByteBuffer
   *  -HeapByteBuffer
   *    -HeapByteBufferR
   *  -MappedByteBuffer
   *    -DirectByteBuffer
   *      -DirectByteBufferR
   *      
   * ByteBuffer是借助Bits类来进行读取写入,Bits会根据大小端进行保存字节.
   */
  public void testByteBuffer() {
    ByteBuffer byteBuffer = ByteBuffer.allocate(1024); printInfo(byteBuffer);
    byteBuffer.put((byte) 27); /** byte 1字节 **/ printInfo(byteBuffer);
    byteBuffer.putInt(27); /** int 4字节 **/ printInfo(byteBuffer);
    byteBuffer.flip();
    byteBuffer.putChar('你'); /** char 2个字节 **/ printInfo(byteBuffer);
    byteBuffer.limit(1024);
    byteBuffer.putChar('好'); /** char 2个字节 **/ printInfo(byteBuffer);
    System.out.println(byteBuffer.get()); /** 取出一个字节 **/ printInfo(byteBuffer);
    System.out.println("大小端:" + byteBuffer.order());
  }

  /**
   * position 是当前位置 
   * limit 是可用大小
   * capacity 是实际大小
   */
  private void printInfo(Buffer buffer) {
    System.out.println(
        "position:" + buffer.position() + "\tlimit:" + buffer.limit() + "\tcapacity:" + buffer.capacity());
  }

}
