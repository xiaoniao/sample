package com.liuzhuang.redis.queue;

import java.util.List;

import redis.clients.jedis.Jedis;

/**
 * 先进先出队列
 */
public class FifoQueueThread extends Thread {

  private static final String FIFO_QUUE_LIST_KEY = "fifoQueue";
  private Jedis loopReadJedis = new Jedis("localhost");
  private Jedis writeJedis = new Jedis("localhost");
  private volatile boolean quit;

  @Override
  public void run() {
    /**
     * 循环阻塞式的获取元素
     */
    while (!quit) {
      System.out.println("loop");
      List<String> list = loopReadJedis.blpop(1, FIFO_QUUE_LIST_KEY);// 单位秒
      if (list == null || list.isEmpty()) {
        continue;
      }
      for (String string : list) {
        System.out.println("#read:" + string);
      }
    }
  }

  /**
   * 列表中添加任务
   */
  public void addToQueue(String content) {
    writeJedis.rpush(FIFO_QUUE_LIST_KEY, content);
  }

  /**
   * 退出
   */
  public void quit() {
    this.quit = true;
  }
}
