package com.example.shardingtable.sharding;

import io.shardingsphere.core.keygen.KeyGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * 测试自定义主键是否是递增的？
 *
 * 在高并发下 插入数据库  id 是自增主键 knowledge_no 是分布式主键。。
 *
 * 结果表明，即便分布式主键是递增的，但分布式主键在插入数据库之后不一定是递增的
 *
 * 一个是程序的先后顺序 一个是数据库插入的先后顺序， 理论上是应该按照程序的先后顺序
 *
 * Created by liuzz on 2018/05/31
 */
public class MyKeyGenerator implements KeyGenerator {
    private Logger log = LoggerFactory.getLogger(MyKeyGenerator.class);

    private int a;

    public MyKeyGenerator() {
        log.info("-------------------- MyKeyGenerator init");
    }

    @Override
    public Number generateKey() {
        a++;
        long result = 100000000L + a;
        log.info("key : {}", result);
        return result;
    }
}
