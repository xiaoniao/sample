package com.liuzhuang.redis.model;

import java.util.List;

public class TimeList {

  static class Time {
    private String articleId;
    private Double score;

    public String getArticleId() {
      return articleId;
    }

    public void setArticleId(String articleId) {
      this.articleId = articleId;
    }

    public Double getScore() {
      return score;
    }

    public void setScore(Double score) {
      this.score = score;
    }
  }

  List<Time> list;

  public List<Time> getList() {
    return list;
  }

  public void setList(List<Time> list) {
    this.list = list;
  }

}
