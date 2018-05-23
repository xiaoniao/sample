package com.liuzhuang.redis.model;

/**
 * 文章
 */
public class Article {

  private String articleId;// id
  private String title;// 标题
  private String link;// 指向文章的网址
  private String poster;// 发布文章的用户
  private Long time;// 文章发布时间
  private Integer votes;// 投票数量

  public Article(String articleId, String title, String link, String poster, Long time, Integer votes) {
    super();
    this.articleId = articleId;
    this.title = title;
    this.link = link;
    this.poster = poster;
    this.time = time;
    this.votes = votes;
  }

  public String getArticleId() {
    return articleId;
  }

  public void setArticleId(String articleId) {
    this.articleId = articleId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getPoster() {
    return poster;
  }

  public void setPoster(String poster) {
    this.poster = poster;
  }

  public Long getTime() {
    return time;
  }

  public void setTime(Long time) {
    this.time = time;
  }

  public Integer getVotes() {
    return votes;
  }

  public void setVotes(Integer votes) {
    this.votes = votes;
  }

  @Override
  public String toString() {
    return "Article [articleId=" + articleId + ", title=" + title + ", link=" + link + ", poster=" + poster + ", time="
        + time + ", votes=" + votes + "]";
  }

}
