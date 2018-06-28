package io.netty.example.definitive.buffer;

import sun.nio.ch.DirectBuffer;

import java.nio.ByteBuffer;

/**
 * Created by liuzz on 2018/06/26
 *
 * nio:
 * heapByteBuffer 使用堆内存，发送数据时，会拷贝到堆外内存
 * directByteBuffer 使用堆外内存
 *
 * netty:
 * ByteBuf
 *  容量可以动态扩展和收缩
 *  API使用方便
 *  支持一些高级特性
 */
public class TestByteBuffer {

    public static void main(String[] args) {

        ByteBuffer heapByteBuffer = ByteBuffer.allocate(1024 * 1024 * 100);

        ByteBuffer directByteBuffer = ByteBuffer.allocateDirect(1024 * 1024 * 100);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (directByteBuffer.isDirect()) {
            ((DirectBuffer) directByteBuffer).cleaner().clean();
        }

        System.out.println("clear!!!!!");

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
