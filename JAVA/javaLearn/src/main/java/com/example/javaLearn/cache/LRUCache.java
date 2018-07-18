package com.example.javaLearn.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by liuzz on 2018/07/12
 *
 * https://tonydeng.github.io/2015/07/16/linkedhashmap-based-lrucache-implementation/
 *
 * https://leokongwq.github.io/2016/10/26/java-LinkedHashMap.html
 *
 * http://colobu.com/2015/09/07/LRU-cache-implemented-by-Java-LinkedHashMap/
 *
 * http://chriswu.me/blog/a-lru-cache-in-10-lines-of-java/
 *
 * 一个使用本地缓存引起的线程阻塞问题
 * http://jenwang.me/14853486232734.html
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private int cacheSize;

    public LRUCache(int cacheSize) {
        super(16, (float) 0.75, true);
        this.cacheSize = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > cacheSize;
    }

    /**
     * 删除最前面的， 获取过一次则追加到最后，新添加的也追加到最后
     */
    public static class LRUCacheTest {

        private static final Logger log = LoggerFactory.getLogger(LRUCacheTest.class);

        private static LRUCache<String, Integer> cache = new LRUCache<>(10);

        public static void main(String[] args) {
            for (int i = 0; i < 10; i++) {
                cache.put("k" + i, i);
            }
            log.info("all cache :'{}'", cache);
            cache.put("k" + 10, 10);
            log.info("After running the LRU algorithm cache :'{}'", cache);

            cache.get("k1");
            cache.get("k1");
            cache.get("k1");
            cache.get("k1");


            cache.put("k" + 11, 11);
            log.info("After running the LRU algorithm cache :'{}'", cache);

            cache.get("k7");
            log.info("After running the LRU algorithm cache :'{}'", cache);
        }
    }
}