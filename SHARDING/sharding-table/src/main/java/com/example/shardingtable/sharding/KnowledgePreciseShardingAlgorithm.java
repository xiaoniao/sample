package com.example.shardingtable.sharding;

import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * Created by liuzz on 2018/05/31
 */
public class KnowledgePreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    private Logger log = LoggerFactory.getLogger(KnowledgePreciseShardingAlgorithm.class);

    public KnowledgePreciseShardingAlgorithm() {
        log.info("-------------------- KnowledgePreciseShardingAlgorithm init");
    }

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        // log.info("-------------------- KnowledgePreciseShardingAlgorithm doSharding");
        long dbIndex = Math.abs(shardingValue.getValue()) % 2;
        for (String each : availableTargetNames) {
            if (each.endsWith(String.valueOf(dbIndex))) {
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }
}
