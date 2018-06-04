package com.example.shardingtable.sharding;

import io.shardingsphere.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * Created by liuzz on 2018/05/31
 */
public class DefaultPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    private Logger log = LoggerFactory.getLogger(DefaultPreciseShardingAlgorithm.class);

    public DefaultPreciseShardingAlgorithm() {
        log.info("-------------------- DefaultPreciseShardingAlgorithm init");
    }

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        long dbIndex = Math.abs(shardingValue.getValue()) % 2;
        for (String each : availableTargetNames) {
            if (each.endsWith(String.valueOf(dbIndex))) {
                // log.info("value : {} --------------------{}", shardingValue.getValue(), each);
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }
}
