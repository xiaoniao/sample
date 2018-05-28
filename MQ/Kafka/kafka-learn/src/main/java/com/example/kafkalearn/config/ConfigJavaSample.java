package com.example.kafkalearn.config;

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
public class ConfigJavaSample {

    @Autowired
    private Listener listener;

    @Autowired
    private KafkaTemplate<Integer, String> template;

    public void testSimple() throws Exception {
        template.send("annotated1", 0, "foo");
        template.flush();
        this.listener.getLatch1().await(10, TimeUnit.SECONDS);
    }

    public static class Listener {

        private final CountDownLatch latch1 = new CountDownLatch(1);

        @KafkaListener(id = "foo", topics = "annotated1")
        public void listen1(String foo) {
            System.out.println(foo);
            this.latch1.countDown();
        }

        public CountDownLatch getLatch1() {
            return latch1;
        }
    }
}
