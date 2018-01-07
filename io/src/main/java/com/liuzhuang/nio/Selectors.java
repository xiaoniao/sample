package com.liuzhuang.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Selectors {

  private static ByteBuffer sendbuffer = ByteBuffer.allocate(1024);
  private static ByteBuffer receivebuffer = ByteBuffer.allocate(1024);

  public static void main(String[] args) throws IOException {
    new Thread(() -> {
      try {
        socket();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }).start();
  }

  private static void socket() throws IOException {
    Selector sel = Selector.open();
    ServerSocketChannel ssc = ServerSocketChannel.open();
    ssc.configureBlocking(false);
    ssc.register(sel, SelectionKey.OP_ACCEPT);
    ServerSocket ss = ssc.socket();
    SocketAddress address = new InetSocketAddress(8999);
    ss.bind(address);
    while (true) {
      int channelNum = sel.select(); // 阻塞方法 轮训SelectionKey
      System.out.println("channelNum:" + channelNum);

      Iterator<SelectionKey> iterator = sel.selectedKeys().iterator();
      while (iterator.hasNext()) {
        SelectionKey key = iterator.next();
        iterator.remove(); // 移除SelectionKey
        handlekey(sel, key);
      }
    }
  }

  private static void handlekey(Selector sel, SelectionKey key) throws IOException {
    // 新连接事件
    if (key.isAcceptable()) {
      System.out.println("isAcceptable:接受新的连接");
      ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
      SocketChannel sc = ssc.accept();
      sc.configureBlocking(false);
      sc.register(sel, SelectionKey.OP_READ); //注册读取事件
    }
    // 读事件
    else if (key.isReadable()) { 
      System.out.println("isReadable:读取来自套接字的数据");
      SocketChannel sc = (SocketChannel) key.channel();
      receivebuffer.clear();
      int count = sc.read(receivebuffer);
      if (count > 0) {
        String receiveData = new String(receivebuffer.array(), 0, count);
        System.out.println("receiveData:" + receiveData);
      }
      sc.register(sel, SelectionKey.OP_WRITE); //注册写事件
    }
    // 写事件
    else if (key.isWritable()) {
      System.out.println("isWritable:写入流");
      SocketChannel sc = (SocketChannel) key.channel();
      sendbuffer.clear();
      sendbuffer.put("你好你好你好你好你好你好你好\r\n".getBytes());
      sendbuffer.flip();
      sc.write(sendbuffer);
      System.out.println("write你好");
      sc.register(sel, SelectionKey.OP_READ); //注册读事件
    }
  }
}
