package com.liuzhuang.redis;

import redis.clients.jedis.Jedis;

public class Strings {

  public static void main(String[] args) {
    Jedis jedis = new Jedis("localhost");
    jedis.set("cache", "1", "accc");
    System.out.println(jedis.get("cache"));
  }
}
