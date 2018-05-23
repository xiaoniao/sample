package com.example.jedissample;

import java.time.Duration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

/**
 * Created by liuzz on 2018/01/03
 *
 * http://www.baeldung.com/jedis-java-redis-client-library
 *
 */
public class JedisSample {

    public static void main(String[] args) {
        Jedis jedis = new Jedis();

        jedis.set("currentYear", "2018");
        String currentYear = jedis.get("currentYear");
        System.out.println(currentYear);
    }

    /**
     * Strings
     */

    /**
     * Lists
     */

    /**
     * Sets
     */

    /**
     * Hashes
     */

    /**
     * Sorted Sets
     */

    /**
     * Transactions
     */

    /**
     * Pipelining
     */

    /**
     * Publish/Subscribe
     */

    /**
     * Subscriber
     */

    /**
     * Publisher
     */

    /**
     * Connection Pooling
     */

    final JedisPoolConfig jedisPoolConfig = buildPoolConfig();

    JedisPool jedisPool = new JedisPool(jedisPoolConfig, Protocol.DEFAULT_HOST);

    private JedisPoolConfig buildPoolConfig() {
        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(128);
        poolConfig.setMaxIdle(128);
        poolConfig.setMinIdle(16);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
        poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setBlockWhenExhausted(true);
        return poolConfig;
    }

    private void userPool() {
        try (Jedis jedis = jedisPool.getResource()) {
            // do operations with jedis resource
        }
    }

    /**
     * Redis Cluster
     */
}
