package io.netty.example.definitive.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.ByteBuffer;

/**
 * Created by liuzz on 2018/06/26
 */
public class TestByteBuf {

    public static void main(String[] args) {

        ByteBuf byteBuf = Unpooled.copiedBuffer("hello".getBytes());

        /*
         * 顺序读写
         */
        byteBuf.readBytes(1);
        byteBuf.writeByte(2);


        /*
         * 随机读写
         */
        byteBuf.setByte(1, 2);
        byteBuf.getByte(1);

        /*
         * ByteBuf 转换成 ByteBuffer
         *
         * 两者共享一个缓冲区内容引用。对ByteBuffer 的读写操作不会修改 ByteBuf 的读写索引
         */
        ByteBuffer byteBuffer = byteBuf.nioBuffer();


        /*
         * ResourceLeakDetector 怎么检查内存泄露？什么是内存泄露
         */

        /*
         * 扩增 -
         *  先倍增再步进的算法
         *
         * AbstractByteBufAllocator calculateNewCapacity 返回新的容量
         *
         * 小于4M  64字节 * 2 倍增
         * 等于4M  返回4M
         * 大于4M  4M ~ 最大值
         */


        /*
         * discardReadBytes
         *
         */

        /*
         * AbstractReferenceCountedByteBuf 引用计数 跟踪对象分配销毁，做自定内存回收。
         *
         * AtomicIntegerFieldUpdater 和 volatile 线程安全做更新值
         */
    }
}
