package com.example.kafkaapi.script.bean;

import java.util.List;

/**
 * Created by liuzz on 2018/05/28
 */
public class Replication {

    private String topic;

    private Integer partition;

    private List<Integer> replicas;


    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getPartition() {
        return partition;
    }

    public void setPartition(Integer partition) {
        this.partition = partition;
    }

    public List<Integer> getReplicas() {
        return replicas;
    }

    public void setReplicas(List<Integer> replicas) {
        this.replicas = replicas;
    }
}
