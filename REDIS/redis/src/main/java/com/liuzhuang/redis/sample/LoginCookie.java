package com.liuzhuang.redis.sample;

import com.liuzhuang.redis.Main;

import redis.clients.jedis.Jedis;

/**
 * 存储用户登录信息
 * 用户的访问时长
 * 已浏览商品
 */
public class LoginCookie {

  private String LOGIN = "login";
  private Jedis jedis = new Jedis("localhost");
  
  public static void main(String[] args) {
    
  }
  
  /**
   * token-user
   */
  public String LoginByToken(String token) {
    return jedis.hget(LOGIN, token);
  }

  
}
