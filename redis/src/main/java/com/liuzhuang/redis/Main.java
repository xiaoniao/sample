package com.liuzhuang.redis;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.BitOP;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis命令参考 http://redisdoc.com/index.html 说明的很详细,每一个类型都有哪些命令.
 */
public class Main {

  public static void main(String[] args) {
    JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");
    try (Jedis jedis = pool.getResource()) {
      //testString(jedis);
      //testSet(jedis);
      testZset(jedis);
      //testList(jedis);
      testHash(jedis);
    }
    pool.close();
  }
  
  private static void testString(Jedis jedis) {
    //set
    String status = jedis.set("name", "redis"); // 插入成功返回字符串 "OK"
    System.out.println(status);
    //get
    String value = jedis.get("name");
    System.out.println(value);
    //del
    long result = jedis.del("name");
    System.out.println(result);
    //incr(对非数字进行自增自减会按0对待)
    result = jedis.incr("name");
    System.out.println(jedis.get("name"));
    //decr
    result = jedis.decr("name");
    System.out.println(jedis.get("name"));
    //incrby
    jedis.incrBy("name", 1000);
    System.out.println(jedis.get("name"));
    //decrby
    jedis.decrBy("name", 2000);
    System.out.println(jedis.get("name"));
    //incrbyfloat
    jedis.incrByFloat("name", 1.1f);
    System.out.println(jedis.get("name"));
    //apend
    jedis.append("name","abcdefghijk");
    System.out.println(jedis.get("name"));
    //getrange
    value = jedis.getrange("name", 0, 1);
    System.out.println(value);
    //setrange
    jedis.setrange("name", 0, "gggggggggggggggg");
    System.out.println(jedis.get("name"));
    //getbit
    boolean bit = jedis.getbit("name", 0);
    System.out.println(bit);
    //setbit
    jedis.setbit("name", 0, true);
    System.out.println(jedis.get("name"));
    //bitcount
    long bitCount = jedis.bitcount("name");
    System.out.println(bitCount);
    //bittop
    jedis.set("key1", "123");
    jedis.set("key2", "456");
    jedis.set("destKey", "destKey");
    jedis.bitop(BitOP.NOT, "destKey", "key1", "key2"); //BitOP op, final String destKey, String... srcKeys
    System.out.println(jedis.get("destKey"));
    //strlen
    long length = jedis.strlen("name");
    System.out.println(length);
  }

  private static void testSet(Jedis jedis) {
    //sadd
    jedis.sadd("order", "1", "2", "3");
    printOrder(jedis);
    //srem
    jedis.srem("order", "2");
    printOrder(jedis);
    //sismember
    boolean result = jedis.sismember("order", "3");
    System.out.println(result);
    //scard
    long count = jedis.scard("order");
    System.out.println(count);
    //smembers
    Set<String> set = jedis.smembers("order");
    System.out.println(set);
    //srandmember
    List<String> list = jedis.srandmember("order", 2);
    System.out.println(list);
    //spop
    String pop = jedis.spop("order");
    System.out.println(pop);
    //smove
    jedis.smove("order", "history", "3");
    System.out.println(jedis.smembers("history"));
    //sdiff差集
    jedis.sadd("colunms:1", "a", "b", "c");
    jedis.sadd("colunms:2", "e", "b", "f");
    set = jedis.sdiff("colunms:1", "colunms:2");
    System.out.println(set);
    set = jedis.sdiff("colunms:2", "colunms:1");
    System.out.println(set);
    //sdiffstore
    jedis.sdiffstore("colunms:3", "colunms:1", "colunms:2");
    System.out.println(jedis.smembers("colunms:3"));
    //sinter交集
    set = jedis.sinter("colunms:1", "colunms:2");
    System.out.println(set);
    //sinterstore
    jedis.sinterstore("colunms:3", "colunms:1", "colunms:2");
    System.out.println(jedis.smembers("colunms:3"));
    //sunion并集
    set = jedis.sunion("colunms:1", "colunms:2");
    System.out.println(set);
    //suionstore
    jedis.sunionstore("colunms:3", "colunms:1", "colunms:2");
    System.out.println(jedis.smembers("colunms:3"));
  }
  
  private static void printOrder(Jedis jedis) {
    System.out.println(jedis.smembers("order"));;
  }

  private static void testZset(Jedis jedis) {

  }

  private static void testList(Jedis jedis) {
    //del
    jedis.del("score", "jack", "rose");
    //lpush
    jedis.lpush("score", "100");
    printScore(jedis);
    //rpush
    jedis.rpush("score", "101");
    printScore(jedis);
    //lpop
    String value = jedis.lpop("score");
    System.out.println(value);
    printScore(jedis);
    //rpop
    value = jedis.rpop("score");
    System.out.println(value);
    printScore(jedis);
    //lindex
    jedis.lpush("score", "102");
    value = jedis.lindex("score", 0);
    System.out.println(value);
    //lrange
    List<String> list = jedis.lrange("score", 0, 1);
    System.out.println(Arrays.toString(list.toArray()));
    //ltrim
    jedis.rpushx("score", "103");
    jedis.rpushx("score", "104");
    printScore(jedis);
    jedis.ltrim("score", 1, 2);
    printScore(jedis);
    //blpop
    list = jedis.blpop(1, "score");
    System.out.println(Arrays.toString(list.toArray()));
    //brpop
    list = jedis.brpop(1, "score");
    System.out.println(Arrays.toString(list.toArray()));
    list = jedis.brpop(1, "score");
    System.out.println(Arrays.toString(list.toArray()));
    //rpoplpush 一个列表的右部加到一个列表的左部
    jedis.rpush("jack", "a", "b", "c");
    jedis.rpush("rose", "e", "f", "g");
    jedis.rpoplpush("jack", "rose");
    System.out.println(jedis.lrange("jack", 0, -1));
    System.out.println(jedis.lrange("rose", 0, -1));
    //brpoplpush
    jedis.brpoplpush("jack", "rose", 1);
    System.out.println(jedis.lrange("jack", 0, -1));
    System.out.println(jedis.lrange("rose", 0, -1));
    //llen
    long length = jedis.llen("rose");
    System.out.println(length);
    //lrem
    jedis.lrem("rose", 2, "c");
    printRose(jedis);
    //lset
    jedis.lset("rose", 2, "pig");
    printRose(jedis);
    //linsert
    jedis.linsert("rose", LIST_POSITION.AFTER, "", "");
    printRose(jedis);
  }
  
  private static void printScore(Jedis jedis) {
    System.out.println(jedis.lrange("score", 0, -1));
  }
  
  private static void printRose(Jedis jedis) {
    System.out.println(jedis.lrange("rose", 0, -1));
  }

  private static void testHash(Jedis jedis) {
    //hset
    jedis.hset("user:1", "name", "redis");
    jedis.hset("user:1", "pwd", "*****");
    //hget
    String name = jedis.hget("user:1", "name");
    System.out.println(name);
    //hdel
    jedis.hdel("user:1", "name");
    //hgetaLL
    Map<String, String> map = jedis.hgetAll("user:1");
    for (String key : map.keySet()) {
      System.out.println(key + ":" + map.get(key));
    }
    
  }
}
