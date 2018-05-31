package com.example.shardingtable.sharding;

import io.shardingjdbc.core.keygen.KeyGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liuzz on 2018/05/31
 */
public class MyKeyGenerator implements KeyGenerator {
    private Logger log = LoggerFactory.getLogger(MyKeyGenerator.class);

    public MyKeyGenerator() {
        log.info("MyKeyGenerator init");
    }

    @Override
    public Number generateKey() {
        log.info("MyKeyGenerator generateKey");
        return 1;
    }
}
