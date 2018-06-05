package com.example.shardingtable.sharding;

import com.google.common.collect.Range;
import io.shardingsphere.core.api.algorithm.sharding.RangeShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.standard.RangeShardingAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * Created by liuzz on 2018/05/31
 */
public class KnowledgeRangeShardingAlgorithm implements RangeShardingAlgorithm<Long> {
    private Logger log = LoggerFactory.getLogger(KnowledgeRangeShardingAlgorithm.class);

    public KnowledgeRangeShardingAlgorithm() {
        log.info("-------------------- KnowledgeRangeShardingAlgorithm init");
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Long> shardingValue) {
        log.info("****************************** DefaultRangeShardingAlgorithm Range");
        Range<Long> range = shardingValue.getValueRange();
        return availableTargetNames;
    }
}
