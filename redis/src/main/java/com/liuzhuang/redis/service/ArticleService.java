package com.liuzhuang.redis.service;

import java.util.List;

import com.liuzhuang.redis.model.Article;

import redis.clients.jedis.Jedis;

public interface ArticleService {

  void setJedis(Jedis jedis);
  
  /**
   * 发布文章
   * @param title
   * @param link
   * @param poster
   * @return
   */
  Article post(String title, String link, String poster);

  /**
   * 获取文章总数量
   * @return
   */
  Long getCount();
  
  /**
   * 获取文章列表
   * @return
   */
  List<Article> getList(Integer currentPage, Integer pageSize);

  /**
   * 获取文章详情
   * @return
   */
  Article getInfo(String articleId);

  /**
   * 投票
   * @param article
   * @param user
   * @return
   */
  Boolean vote(String article, String user);
}
