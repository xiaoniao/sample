//package com.example.kafkalearn.boot;
//
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.annotation.TopicPartition;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by liuzz on 2018/05/16
// */
//@Component
//public class BootJavaSample {
//    private Logger logger = LoggerFactory.getLogger(BootJavaSample.class);
//
//    @Autowired
//    private KafkaTemplate<String, String> template;
//
//    private final CountDownLatch latch = new CountDownLatch(3);
//
//
//    private final CountDownLatch latch2 = new CountDownLatch(1);
//
//    private int count;
//
//    public void sendMsg() throws Exception {
//        this.template.send("myTopic", "foo1");
//        this.template.send("myTopic", "foo2");
//        this.template.send("myTopic", "foo3");
//        latch.await(60, TimeUnit.SECONDS);
//        logger.info("All received");
//    }
//
//    @KafkaListener(topics = "myTopic")
//    private void listen(ConsumerRecord<?, ?> cr) throws Exception {
//        logger.info("【myTopic】{}, {} ThreadName: {}", cr.toString(), count++, Thread.currentThread().getName());
//        latch.countDown();
//    }
//
//    /**
//     * 不指定分区会发送到哪个分区？
//     *
//     * https://leokongwq.github.io/2017/02/27/mq-kafka-producer-partitioner.html
//     *
//     * 1、如果不手动指定分区选择策略类，则会使用默认的分区策略类。
//     * 2、如果不指定消息的key，则消息发送到的分区是随着时间不停变换的。
//     * 3、如果指定了消息的key，则会根据消息的hash值和topic的分区数取模来获取分区的。
//     * 4、如果应用有消息顺序性的需要，则可以通过指定消息的key和自定义分区类来将符合某种规则的消息发送到同一个分区。同一个分区消息是有序的，同一个分区只有一个消费者就可以保证消息的顺序性消费。
//     */
//    public void sendMsgWithOutPartition() throws Exception {
//        this.template.send("liuzz-article", "2011", "spring");
//        latch2.await();
//    }
//
//    /**
//     * 发送指定分区
//     */
//    public void sendMsgWithPartition() throws Exception {
//        this.template.send("liuzz-article", 0, "0518", "boot3");
//        latch2.await();
//    }
//
//    /**
//     * 接受主题指定分区消息
//     *
//     * 为什么每次启动 都会受到消息？
//     * 【liuzz-article】ConsumerRecord(topic = liuzz-article, partition = 1, offset = 2, CreateTime = 1526623532262, serialized key size = 4, serialized value size = 6, headers = RecordHeaders(headers = [], isReadOnly = false), key = 2011, value = spring)
//     */
//    @KafkaListener(topicPartitions = {@TopicPartition(topic = "liuzz-article", partitions = {"1"})})
//    private void articleListener(ConsumerRecord<?, ?> cr) throws Exception {
//        logger.info("articleListener【liuzz-article】{} ThreadName: {}", cr.toString(), Thread.currentThread().getName());
//        latch2.countDown();
//    }
//
//    @KafkaListener(topics = "liuzz-article")
//    private void articleListener2(ConsumerRecord<?, ?> cr) throws Exception {
//        logger.info("articleListener2【liuzz-article】{} ThreadName: {}", cr.toString(), Thread.currentThread().getName());
//        latch2.countDown();
//    }
//}
