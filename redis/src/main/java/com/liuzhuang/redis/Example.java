package com.liuzhuang.redis;

import java.util.List;

import com.liuzhuang.redis.model.Article;
import com.liuzhuang.redis.service.ArticleService;
import com.liuzhuang.redis.service.impl.ArticleServiceImpl;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Example {

  private ArticleService articleService = new ArticleServiceImpl();

  public Example() {
    JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");
    try (Jedis jedis = pool.getResource()) {
      articleService.setJedis(jedis);
    }
    pool.close();
  }

  public static void main(String[] args) {
    Example example = new Example();
    for (int i = 0; i < 3; i++) {
      example.articleService.post("hello", "http://wwww.www.com", "user:1001");
    }
    long totalCount = example.articleService.getCount();
    int pageSize = 12;
    long totalPage = totalCount / pageSize + (totalCount % pageSize > 0 ? 1 : 0);
    System.out.println("totalCount:" + totalCount + " totalPage:" + totalPage);
    for (int currentPage = 0; currentPage < totalPage; currentPage++) {
      System.out.println(currentPage + "页");
      List<Article> list = example.articleService.getList(currentPage, pageSize);
      for (Article article : list) {
        System.out.println(article);
      }
    }
  }
}
