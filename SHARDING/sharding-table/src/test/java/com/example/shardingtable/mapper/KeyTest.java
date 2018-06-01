package com.example.shardingtable.mapper;

import com.example.shardingtable.ShardingTableApplication;
import io.shardingsphere.core.keygen.DefaultKeyGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liuzz on 2018/06/01
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ShardingTableApplication.class)
public class KeyTest {

    /**
     * http://shardingjdbc.io/document/legacy/2.x/cn/01-start/faq/
     * FAG 9
     * Sharding-JDBC采用snowflake算法作为默认的分布式分布式自增主键策略，用于保证分布式的情况下可以无中心化的生成不重复的自增序列。因此自增主键可以保证递增，但无法保证连续。
     * 而snowflake算法的最后4位是在同一毫秒内的访问递增值。因此，如果毫秒内并发度不高，最后4位为零的几率则很大。因此并发度不高的应用生成偶数主键的几率会更高。
     */
    @Test
    public void testDefaultKeyGenerator() {
        DefaultKeyGenerator defaultKeyGenerator = new DefaultKeyGenerator();
        for (int i = 0; i < 100; i++) {
            System.out.println(defaultKeyGenerator.generateKey());
        }
    }
}
