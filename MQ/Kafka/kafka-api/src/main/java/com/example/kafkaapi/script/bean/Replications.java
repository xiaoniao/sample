package com.example.kafkaapi.script.bean;

import java.util.List;

/**
 * Created by liuzz on 2018/05/28
 */
public class Replications {

    private Integer version;

    private List<Replication> partitions;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public List<Replication> getPartitions() {
        return partitions;
    }

    public void setPartitions(List<Replication> partitions) {
        this.partitions = partitions;
    }
}
