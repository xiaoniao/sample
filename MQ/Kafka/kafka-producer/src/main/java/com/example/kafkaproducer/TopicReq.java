package com.example.kafkaproducer;

/**
 * Created by liuzz on 2018/05/17
 */
public class TopicReq {

    private String topic;

    private String data;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
