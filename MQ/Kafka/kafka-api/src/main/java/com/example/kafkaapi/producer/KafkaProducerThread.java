package com.example.kafkaapi.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.concurrent.CountDownLatch;

/**
 * Created by liuzz on 2018/05/22
 *
 *
 * 为了提升Kafka 发送消息的吞吐量，在数据量比较大同时对消息顺序也没有严格要求的情况下，可以来用多线程的方式
 *
 * 实现多线程生产者一般有两种方式
 *      只实例化一个KafkaProducer对象运行多个线程共享该生产者发送消息
 *      实例化多个KafkaProducer 对象。由于KafkaProducer是线程安全，
 *
 * 经验证多个线程共享一个实例比每个线程各自实例化一个KafkaProducer 对象在性能上要好很多
 *
 */
public class KafkaProducerThread implements Runnable {

    private CountDownLatch countDownLatch;

    private KafkaProducer<String, String> kafkaProducer;

    private ProducerRecord<String, String> producerRecord;

    public KafkaProducerThread(CountDownLatch countDownLatch, KafkaProducer<String, String> kafkaProducer, ProducerRecord<String, String> producerRecord) {
        this.countDownLatch = countDownLatch;
        this.kafkaProducer = kafkaProducer;
        this.producerRecord = producerRecord;
    }

    @Override
    public void run() {
        kafkaProducer.send(producerRecord, (metadata, exception) -> {
            countDownLatch.countDown();
            if (exception != null) {
                //System.out.println("异常" + exception.getMessage());
                return;
            }
            if (metadata != null) {
                //System.out.println("partition:" + metadata.partition() + " offset:" + metadata.offset());
            }
        });
    }
}
