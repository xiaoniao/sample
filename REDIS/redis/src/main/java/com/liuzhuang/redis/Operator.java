package com.liuzhuang.redis;


import java.awt.RenderingHints.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * redis操作命令
 */
public interface Operator {

  //增加
  void add();

  //删除
  void dele();

  //修改
  void modify();

  //查询
  void query();
  
  //其他
  void other();

  Jedis jedis = new Jedis("localhost");

  public static void main(String[] args) {
    Operator operate = null;
    operate = new StringCommand();
    operate = new ListCommand();
    operate = new SetCommand();
    operate = new ZsetCommand();
    operate = new HashCommand();
    operate.add();
    operate.modify();
    operate.query();
    operate.dele();
  }

  /**
   *  ------  ------ 
   * |   k  ||   v  |
   *  ------   ------
   */
  static class StringCommand implements Operator {

	private String key = "title";

    @Override
    public void add() {
      String value = jedis.set(key, "乐视这个声明有些难以自圆其说啊，比如“在易到单独贷款困难情况下，");
      System.out.println(value);
    }

    @Override
    public void dele() {
      Long result = jedis.del(key);
      System.out.println(result);
    }

    @Override
    public void modify() {
      String value = jedis.set(key, "乐视控股以名下乐视大厦为抵押物，以易到为主体");
      System.out.println(value);
    }

    @Override
    public void query() {
      String value = jedis.get(key);
      System.out.println(value);
    }
    
    @Override
    public void other() {
      
    }
  }

  /**
   *  ------  ------  ------  ------  ------
   * |   k  ||   1  ||   2  ||  2   ||   4  |
   *  ------   ------  ------  ------  ------
   */
  static class ListCommand implements Operator {
	
	private String key = "list";

    @Override
    public void add() {
      //lpush
      for (int i = 5; i > 0; i--) {
        Long result = jedis.lpush(key, "article:" + i);
        System.out.println("lpush:" + result);
      }
      //rpush
      for (int i = 6; i <= 10; i++) {
        Long result = jedis.rpush(key, "article:" + i);
        System.out.println("rpush:" + result);
      }
    }

    @Override
    public void dele() {
      //lpop
      String value = jedis.lpop(key);
      System.out.println("lpop:" + value);
      
      //rpop
      value = jedis.rpop(key);
      System.out.println("rpop:" + value);
      
      //lrem 删除列表中的某一项 cout是指删除几个这样的值 >0从左往右 <0从右往左 =0全部删除
      Long result = jedis.lrem(key, 1, "article:6");
      System.out.println("lrem:" + result + " 结果集:" + jedis.lrange("list", 0, -1));
      
      //ltrim
      value = jedis.ltrim(key, 0, 1);
      System.out.println("ltrim:" + value + " 结果集:" + jedis.lrange("list", 0, -1));
      
      //del
      result = jedis.del(key);
      System.out.println("del:" + result);
    }

    @Override
    public void modify() {
      //lset
      String reuslt = jedis.lset(key, 0, "article:1111");
      System.out.println("lset:" + reuslt);
    }

    @Override
    public void query() {
      //lrange
      List<String> result = jedis.lrange(key, 0, -1);
      System.out.println("lrange:" + result);
      
      //lindex
      String value = jedis.lindex(key, 0);
      System.out.println("lindex:" + value);
      
      //llen
      Long length = jedis.llen(key);
      System.out.println("llen:" + length);
    }

    @Override
    public void other() {
      
    }
  }

  /**
   *  ------  ------  ------  ------  ------
   * |   k  ||   c  ||   a  ||  d   ||   b  |
   *  ------   ------  ------  ------  ------
   */
  static class SetCommand implements Operator {

    @Override
    public void add() {
      //sadd
      Long result = jedis.sadd("set", "a", "b", "c");
      System.out.println("sadd:" + result);
    }

    @Override
    public void dele() {
      //srem
      Long result = jedis.srem("set", "c");
      System.out.println("srem:" + result + " 结果集:" + jedis.smembers("set"));
      
      //spop
      String value = jedis.spop("set");
      System.out.println("spop:" + value + " 结果集:" + jedis.smembers("set"));
      
      //smove
      result = jedis.smove("set", "set_2", "b");
      System.out.println("smove:" + result);
      System.out.println(jedis.smembers("set"));
      System.out.println(jedis.smembers("set_2"));
      
      //del
      result = jedis.del("set", "set_2");
      System.out.println("del:" + result);
    }

    @Override
    public void modify() {
      
    }

    @Override
    public void query() {
      //smembers
      Set<String> set = jedis.smembers("set");
      System.out.println("smembers:" + set);
      
      //sismember
      Boolean isExist = jedis.sismember("set", "a");
      System.out.println("sismember:" + isExist);
      
      //scard 集合大小
      Long count = jedis.scard("set");
      System.out.println("scard:" + count);
      
      //srandmember 随机返回个数
      List<String> list = jedis.srandmember("set", 2);
      System.out.println("srandmember:" + list);
    }

    @Override
    public void other() {
      
    }
  }

  /**
   *  ------  --------  -------  -------  ---------
   * |   k  ||   c 7  ||   a 8 ||  d 9  ||   b 10  |
   *  ------   -------  -------  -------  ---------
   */
  static class ZsetCommand implements Operator {

    @Override
    public void add() {
      //zadd
      Long result = jedis.zadd("zset", 7, "c");
      System.out.println("zadd:" + result);
      result = jedis.zadd("zset", 8, "a");
      System.out.println("zadd:" + result);
      result = jedis.zadd("zset", 9, "d");
      System.out.println("zadd:" + result);
      result = jedis.zadd("zset", 10, "b");
      System.out.println("zadd:" + result);
    }

    @Override
    public void dele() {
      //zrem
      Long result = jedis.zrem("zset", "b");
      System.out.println("zrem:" + result);
      
      //del
      result = jedis.del("zset");
      System.out.println("del:" + result);
    }

    @Override
    public void modify() {
      //zadd
      Double result = jedis.zincrby("zset", 99.0, "b");
      System.out.println("zadd:" + result);
    }

    @Override
    public void query() {
      //zcard
      Long count = jedis.zcard("zset");
      System.out.println("zcard:" + count);
      
      //zcount
      count = jedis.zcount("zset", 88, 99);
      System.out.println("zcount:" + count);
      
      //zrank
      Long rank = jedis.zrank("zset", "c");
      System.out.println("zrank:" + rank);
      
      //zscore
      Double score = jedis.zscore("zset", "c");
      System.out.println("zscore:" + score);
      
      //zrange
      Set<String> set = jedis.zrange("zset", 0, -1);
      System.out.println("zrange:" + set);
      
      //zrangeByScore
      set = jedis.zrangeByScore("zset", 7, 9);
      System.out.println("zrangeByScore:" + set);
    }

    @Override
    public void other() {
      
    }
  }

  static class HashCommand implements Operator {

    private String key = "article:1";
    
    @Override
    public void add() {
      //hset
      Long result = jedis.hset(key, "name", "jack");
      System.out.println("hset:" + result);
      result = jedis.hset(key, "age", "18");
      System.out.println("hset:" + result);
      
      //hmset
      Map<String, String> hash = new HashMap<>();
      hash.put("city", "杭州");
      String v = jedis.hmset(key, hash);
      System.out.println("hmset:" + v);
      
    }

    @Override
    public void dele() {
      //hdel
      Long result = jedis.hdel(key, "name");
      System.out.println("hdel:" + result);
      
      //del
      jedis.del(key);
    }

    @Override
    public void modify() {

    }

    @Override
    public void query() {
      //hget
      String name = jedis.hget(key, "name");
      System.out.println("hget:name:" + name);
      
      //hgetAll
      Map<String, String> result = jedis.hgetAll(key);
      for (String k : result.keySet()) {
        System.out.println("hgetAll:" + k + ":" + result.get(k));
      }
      
      //hlen
      Long count = jedis.hlen(key);
      System.out.println("hlen:" + count);
      
      //hexists
      Boolean b = jedis.hexists(name, "name");
      System.out.println("hexists:" + b);
      
      //hkeys
      Set<String> keys = jedis.hkeys(key);
      System.out.println("hkeys:" + keys);
      
      //hvals
      List<String> vals = jedis.hvals(key);
      System.out.println("hval:" + vals);
    }

    @Override
    public void other() {
      
    }
  }
}
