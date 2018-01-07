package com.liuzhuang.redis.sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * 
 * 文章投票系统业务逻辑
 * 
 * 发布文章:
 * 	标题
 *  网址
 *  用户
 *  发布时间
 *  投票数量
 *  
 * 文章列表：
 * 	按添加时间排序，按评分排序
 * 
 * 分值计算规则：
 * 	文章支持票数乘以一个常量，然后加上文章发布时间
 *
 *******
 * 分页使用有序集合的zrevrange zrange命令
 */
public class ArticleSystem {
	
	/**
	 * 结构名称
	 */
	private static final String KEY_ARTICLE = "article:";
	private static final String KEY_VOTE = "vote:";
	private static final String KEY_SORT_SCORE = "sroce:";
	private static final String KEY_SORT_TIME = "time:";
	
	// 分组
	private static final String KEY_GROUP = "group:";
	private static final String KEY_GROUP_ALL = "group:all";
	
	/**
	 * 字段名
	 */
	private static final String FIELD_ARTICLE_TITLE = "title";
	private static final String FIELD_ARTICLE_LINK = "link";
	private static final String FIELD_ARTICLE_USER = "user";
	private static final String FIELD_ARTICLE_NOW = "now";
	private static final String FIELD_ARTICLE_VOTES = "votes";
	
	private static final String DEFAULT_VOTE = "1";
	private static final int VOTE_SCORE = 432; // 投票分数
	private static final int ONE_WEEK_IN_MILLIS = 7 * 86400 * 1000; // 一周多少毫秒
	private static final int PAGE_SIZE = 10;

	Jedis jedis = new Jedis("localhost");
	
	public static void main(String[] args) {
		ArticleSystem articleSystem = new ArticleSystem();
		
		// 添加文章
		Long id = articleSystem.addArticle("userName", "走向共和", "www.google.com");
		
		// 投票
		articleSystem.vote("userName", id);
		
		// 总数量
		System.out.println("count:" + articleSystem.count(KEY_SORT_TIME));
		
		// 分页查询
		List<Map<String, String>> list = articleSystem.getPage(1, KEY_SORT_TIME);
		
		// 打印结果集
		articleSystem.printArticle(list);
		
		// 添加到分组中
		articleSystem.addToGroup(KEY_ARTICLE + id, "computer");
	}
	
	/**
	 * 文章用hash结构
	 * 文章列表用zset结构
	 */
	public Long addArticle(String user, String title, String link) {
		long now = System.currentTimeMillis();
		
		// 添加文章
		Long id = jedis.incr(KEY_ARTICLE);
		String article = KEY_ARTICLE + id;
		Map<String, String> hash = new HashMap<>();
		hash.put(FIELD_ARTICLE_TITLE, title);
		hash.put(FIELD_ARTICLE_LINK, link);
		hash.put(FIELD_ARTICLE_USER, user);
		hash.put(FIELD_ARTICLE_NOW, String.valueOf(now));
		hash.put(FIELD_ARTICLE_VOTES, DEFAULT_VOTE);
		jedis.hmset(article, hash);
		
		// 添加到文章列表中 按时间排序
		jedis.zadd(KEY_SORT_TIME, now, article);
		// 添加到文章列表中 按评分排序
		jedis.zadd(KEY_SORT_SCORE, now + VOTE_SCORE, article);
		return id;
	}
	
	/**
	 * 投票记录用set结构
	 */
	public void vote(String user, Long articleId) {
		// 判断文章是否还可以投票 超过一周就不能投票哦了
		Double time = jedis.zscore(KEY_SORT_TIME, KEY_ARTICLE + articleId);
		if (System.currentTimeMillis() - time > ONE_WEEK_IN_MILLIS) {
			return;
		}
		
		// 判断是否投过票
		Long result = jedis.sadd(String.valueOf(KEY_VOTE + articleId), user);//0代表已存在，1代表插入成功，不用jedis.sismember();来判断是否已经投过票了
		if (result == 0) {
			System.out.println("已经投过票了");
			return;
		}
		if (result == 1) {
			// 给评分文章列表加分
			jedis.zincrby(KEY_SORT_SCORE, VOTE_SCORE, KEY_ARTICLE + articleId);
			// 给文章投票数量加1
			jedis.hincrBy(KEY_ARTICLE + articleId, FIELD_ARTICLE_VOTES, 1L);
		}
	}
	
	/**
	 * 添加到文章分组中
	 * 
	 * 分组使用set结构
	 */
	public void addToGroup(String article, String groupType) {
		jedis.sadd(KEY_GROUP_ALL, article);
		jedis.sadd(KEY_GROUP + groupType, article);
	}
	
	/**
	 * 返回总数量
	 */
	public Long count(String sortKey) {
		return jedis.zcard(sortKey);
	}
	
	/**
	 * 返回文章列表
	 */
	public List<Map<String, String>> getPage(Integer currentPage, String sortKey) {
		List<Map<String, String>> result = new ArrayList<>();
		long start = (currentPage - 1) * PAGE_SIZE;
		long end = start + PAGE_SIZE - 1;
		System.out.println("currentPage:" + currentPage + " start:" + start + " end:" + end);
		Set<String> ids = jedis.zrevrange(sortKey, start, end);
		for (String article : ids) {
			result.add(getArticle(article));
		}
		return result;
	}
	
	/**
	 * 获得文章详情
	 */
	public Map<String, String> getArticle(String article) {
		Map<String, String> fieldMap = jedis.hgetAll(article);
		fieldMap.put("id", article);
		return fieldMap;
	}
	
	public void printArticle(List<Map<String, String>> list) {
		for (Map<String, String> map : list) {
			printArticle(map);
		}
	}
	
	public void printArticle(Map<String, String> map) {
		System.out.println();
		System.out.println(map.get("id"));
		for (String key : map.keySet()) {
			if (key.equals("id")) {
				continue;
			}
			System.out.println(key + ":" + map.get(key));
		}
	}
}
