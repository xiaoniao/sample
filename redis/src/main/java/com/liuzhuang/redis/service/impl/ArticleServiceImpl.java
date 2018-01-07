package com.liuzhuang.redis.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.liuzhuang.redis.model.Article;
import com.liuzhuang.redis.service.ArticleService;

import redis.clients.jedis.Jedis;

public class ArticleServiceImpl implements ArticleService {

  private Jedis jedis;

  @Override
  public Article post(String title, String link, String poster) {
    // 添加文章
    Long id = jedis.incr("article:");
    String articleId = "article:" + id;
    jedis.hset(articleId, "title", title);
    jedis.hset(articleId, "link", link);
    jedis.hset(articleId, "poster", poster);
    jedis.hset(articleId, "time", String.valueOf(new Date().getTime()));
    jedis.hset(articleId, "votes", "0");
    
    // 添加到文章列表
    Map<String, Double> scoreMembers = new HashMap<>();
    scoreMembers.put(articleId, Double.valueOf(new Date().getTime()));
    jedis.zadd("time", scoreMembers);
    
    Article result = new Article(articleId, title, link, poster, new Date().getTime(), 0);
    return result;
  }

  @Override
  public Long getCount() {
    return jedis.zcard("time");
  }
  
  /**
   * zset 才有分页功能
   * currentPage从1开始
   */
  @Override
  public List<Article> getList(Integer currentPage, Integer pageSize) {
    long start = currentPage * pageSize;
    long end = start + pageSize - 1;
    List<Article> result = new ArrayList<>();
    Set<String> sets = jedis.zrange("time", start, end);
    for (String articleId : sets) {
      result.add(getInfo(articleId));
    }
    return result;
  }

  @Override
  public Article getInfo(String articleId) {
    Article article = new Article(
        articleId, 
        jedis.hget(articleId, "title"), 
        jedis.hget(articleId, "link"), 
        jedis.hget(articleId, "poster"), 
        Long.valueOf(jedis.hget(articleId, "time")), 
        Integer.valueOf(jedis.hget(articleId, "votes")));
    return article;
  }

  @Override
  public Boolean vote(String article, String user) {
    return null;
  }

  @Override
  public void setJedis(Jedis jedis) {
    this.jedis = jedis;
  }
}
