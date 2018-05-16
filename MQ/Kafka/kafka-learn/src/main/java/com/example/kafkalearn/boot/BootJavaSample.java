package com.example.kafkalearn.boot;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by liuzz on 2018/05/16
 */
@Component
public class BootJavaSample {
    private Logger logger = LoggerFactory.getLogger(BootJavaSample.class);

    @Autowired
    private KafkaTemplate<String, String> template;

    private final CountDownLatch latch = new CountDownLatch(3);

    private int count;

    public void sendMsg() throws Exception {
        this.template.send("myTopic", "foo1");
        this.template.send("myTopic", "foo2");
        this.template.send("myTopic", "foo3");
        latch.await(60, TimeUnit.SECONDS);
        logger.info("All received");
    }

    @KafkaListener(topics = "myTopic")
    private void listen(ConsumerRecord<?, ?> cr) throws Exception {
        logger.info("【myTopic】{}, {}", cr.toString(), count++);
        latch.countDown();
    }

}
