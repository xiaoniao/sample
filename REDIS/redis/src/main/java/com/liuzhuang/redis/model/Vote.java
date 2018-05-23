package com.liuzhuang.redis.model;

import java.util.List;

/**
 * 投票记录
 */
public class Vote {

  private String articleId;
  private List<String> list;

  public String getArticleId() {
    return articleId;
  }

  public void setArticleId(String articleId) {
    this.articleId = articleId;
  }

  public List<String> getList() {
    return list;
  }

  public void setList(List<String> list) {
    this.list = list;
  }

}
