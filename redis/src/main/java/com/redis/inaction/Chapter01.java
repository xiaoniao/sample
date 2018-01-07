package com.redis.inaction;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ZParams;

import java.util.*;

public class Chapter01 {
	private static final int ONE_WEEK_IN_SECONDS = 7 * 86400; // 一周多少秒
	private static final int VOTE_SCORE = 432; // 投票分数
	private static final int ARTICLES_PER_PAGE = 25; // 每页多少条数据

	public static final void main(String[] args) {
		new Chapter01().run();
	}

	public void run() {
		Jedis conn = new Jedis("localhost");
		conn.select(15);

		/**
		 * 添加文章
		 */
		String articleId = postArticle(conn, "username", "A title", "http://www.google.com");
		System.out.println("We posted a new article with id: " + articleId);
		System.out.println("Its HASH looks like:");
		Map<String, String> articleData = conn.hgetAll("article:" + articleId);
		for (Map.Entry<String, String> entry : articleData.entrySet()) {
			System.out.println("  " + entry.getKey() + ": " + entry.getValue());
		}

		System.out.println();

		/**
		 * 投票
		 */
		articleVote(conn, "other_user", "article:" + articleId);
		String votes = conn.hget("article:" + articleId, "votes");
		System.out.println("We voted for the article, it now has votes: " + votes);
		assert Integer.parseInt(votes) > 1;

		System.out.println("The currently highest-scoring articles are:");
		List<Map<String, String>> articles = getArticles(conn, 1);
		printArticles(articles);
		assert articles.size() >= 1;

		addGroups(conn, articleId, new String[] { "new-group" });
		System.out.println("We added the article to a new group, other articles include:");
		articles = getGroupArticles(conn, "new-group", 1);
		printArticles(articles);
		assert articles.size() >= 1;
	}

	/**
	 * 添加文章
	 * 
	 * @param conn
	 * @param user
	 * @param title
	 * @param link
	 * @return
	 */
	public String postArticle(Jedis conn, String user, String title, String link) {

		// 生成主键id
		String articleId = String.valueOf(conn.incr("article:"));

		// 添加投票记录
		String voted = "voted:" + articleId;
		conn.sadd(voted, user);
		conn.expire(voted, ONE_WEEK_IN_SECONDS);

		// 添加文章
		long now = System.currentTimeMillis() / 1000;
		String article = "article:" + articleId;
		HashMap<String, String> articleData = new HashMap<String, String>();
		articleData.put("title", title);
		articleData.put("link", link);
		articleData.put("user", user);
		articleData.put("now", String.valueOf(now));
		articleData.put("votes", "1");
		conn.hmset(article, articleData);

		// 添加按分数排列序列
		conn.zadd("score:", now + VOTE_SCORE, article);

		// 添加按时间排列序列
		conn.zadd("time:", now, article);

		return articleId;
	}

	/**
	 * 给文章投票
	 * 
	 * @param conn
	 * @param user
	 * @param article
	 */
	public void articleVote(Jedis conn, String user, String article) {
		long cutoff = (System.currentTimeMillis() / 1000) - ONE_WEEK_IN_SECONDS;
		if (conn.zscore("time:", article) < cutoff) {
			return;
		}

		String articleId = article.substring(article.indexOf(':') + 1);
		if (conn.sadd("voted:" + articleId, user) == 1) {
			conn.zincrby("score:", VOTE_SCORE, article);
			conn.hincrBy(article, "votes", 1l);
		}
	}

	/**
	 * 分页获取文章列表
	 */
	public List<Map<String, String>> getArticles(Jedis conn, int page) {
		return getArticles(conn, page, "score:");
	}

	/**
	 * 分页获取文章列表
	 * 
	 * @param conn
	 * @param page
	 * @param order
	 * @return
	 */
	public List<Map<String, String>> getArticles(Jedis conn, int page, String order) {
		int start = (page - 1) * ARTICLES_PER_PAGE;
		int end = start + ARTICLES_PER_PAGE - 1;

		// 分段获取 zset
		Set<String> ids = conn.zrevrange(order, start, end);
		List<Map<String, String>> articles = new ArrayList<Map<String, String>>();
		for (String id : ids) {
			Map<String, String> articleData = conn.hgetAll(id);
			articleData.put("id", id);
			articles.add(articleData);
		}

		return articles;
	}

	/**
	 * 添加到分组中
	 * 
	 * @param conn
	 * @param articleId
	 * @param toAdd
	 */
	public void addGroups(Jedis conn, String articleId, String[] toAdd) {
		String article = "article:" + articleId;
		for (String group : toAdd) {
			conn.sadd("group:" + group, article);
		}
	}

	/**
	 * 根据分组获得文章
	 */
	public List<Map<String, String>> getGroupArticles(Jedis conn, String group, int page) {
		return getGroupArticles(conn, group, page, "score:");
	}

	/**
	 * 根据分组获得文章
	 * 
	 * @param conn
	 * @param group
	 * @param page
	 * @param order
	 * @return
	 */
	public List<Map<String, String>> getGroupArticles(Jedis conn, String group, int page, String order) {
		String key = order + group;
		if (!conn.exists(key)) {
			ZParams params = new ZParams().aggregate(ZParams.Aggregate.MAX);
			conn.zinterstore(key, params, "group:" + group, order);
			conn.expire(key, 60);
		}
		return getArticles(conn, page, key);
	}

	/**
	 * 打印文章信息
	 */
	private void printArticles(List<Map<String, String>> articles) {
		for (Map<String, String> article : articles) {
			System.out.println("  id: " + article.get("id"));
			for (Map.Entry<String, String> entry : article.entrySet()) {
				if (entry.getKey().equals("id")) {
					continue;
				}
				System.out.println("    " + entry.getKey() + ": " + entry.getValue());
			}
		}
	}
}
