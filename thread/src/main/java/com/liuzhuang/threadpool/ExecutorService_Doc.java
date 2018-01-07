package com.liuzhuang.threadpool;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ExecutorService接口都提供了什么功能
 * 
 * 该接口继承自Executor，提供了中止任务和Future返回值追踪异步任务（比如取消任务，获取任务执行结果）的功能。
 *
 * shutdown()允许之前添加的任务继续被执行
 * shurdownNow() 不允许再添加任务，并尝试结束掉正在执行的任务，发出中断线程信号.
 * 
 * submit() 返回了Future对象，可以用来取消任务
 * 
 * invokeAny() invokeAll()，也是最常见的使用形式，用来批量提交任务，并等待一个或多个任务来执行完成。
 * 
 * shutdown - 仍然按顺序执行之前添加的
 * shutdownNow
 * awaitTermination - 等待所有任务执行完毕， 在shutdown之后调用，阻塞着等待所有的任务执行完毕，或者超时抛出异常，或当前线程被中断。
 * isShutdown
 * isTerminated
 * submit - 提交任务，有返回值
 * invokeAll 批量执行任务
 */
public class ExecutorService_Doc {

  public static void main(String[] args) throws IOException, InterruptedException {
    NetworkService networkService = new NetworkService(8899, 10);
    new Thread(networkService).start();

    Thread.sleep(1000);
    networkService.shutdownAndAwaitTermination();
  }

  /**
   * socket服务器
   */
  static class NetworkService implements Runnable {

    private final ServerSocket serverSocket;
    private final ExecutorService pool;

    public NetworkService(int port, int poolSize) throws IOException {
      serverSocket = new ServerSocket(port);
      pool = Executors.newFixedThreadPool(poolSize);
    }

    @Override
    public void run() {
      try {
        for (;;) {
          System.out.println("--wait--");
          pool.execute(new Handler(serverSocket.accept()));
        }
      } catch (IOException ex) {
        pool.shutdown();
      }
    }

    /**
     * 测试ExecutorService的api功能
     */
    void shutdownAndAwaitTermination() {
      // 禁止任务被提交
      System.out.println("禁止再提交任务");
      pool.shutdown();
      try {
        // 等待60秒，检查所有任务是否被终止
        boolean terminated = pool.awaitTermination(60, TimeUnit.SECONDS);
        System.out.println("terminated:" + terminated);
        if (!terminated) {
          // 取消掉正在执行的任务
          pool.shutdownNow();
          // 等待60秒，检查所有任务是否被终止
          if (!pool.awaitTermination(60, TimeUnit.SECONDS))
            System.err.println("Pool did not terminate");
        }
      } catch (InterruptedException ie) {
        pool.shutdownNow();
        Thread.currentThread().interrupt();
      }
    }
  }

  /**
   * 处理socket请求
   */
  static class Handler implements Runnable {
    private final Socket socket;

    Handler(Socket socket) {
      this.socket = socket;
    }

    @Override
    public void run() {
      try {
        Reader reader = new InputStreamReader(socket.getInputStream());

        int chars;
        StringBuffer stringBuffer = new StringBuffer();
        int index = 0;
        while ((chars = reader.read()) != -1) { // 注意socket是双向流，两个流是持续交互，所以这里的read方法会阻塞主，需要我们判断http协议请求结束标志来结束
          index++;
          stringBuffer.append((char) chars);
          // http请求结束标志
          if (index > 4 && stringBuffer.substring(index - 4, index).equals("\r\n\r\n")) {
            break;
          }
        }
        System.out.println(stringBuffer);

        /**
         * 如果在Chrome上测试需要返回成http协议才能在界面上显示出来。
         */
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("HTTP/1.1 200 OK\r\n".getBytes());
        outputStream.write("Connection:keep-alive\r\n".getBytes());
        outputStream.write("Content-Type:application/json;charset=UTF-8\r\n".getBytes());
        outputStream.write("\r\n".getBytes());
        outputStream.write("你好".getBytes());
        outputStream.flush();
        outputStream.close();

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
