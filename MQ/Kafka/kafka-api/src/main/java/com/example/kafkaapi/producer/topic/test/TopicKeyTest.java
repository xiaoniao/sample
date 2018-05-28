package com.example.kafkaapi.producer.topic.test;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by liuzz on 2018/05/22
 */
public class TopicKeyTest {
    private static Logger log = LoggerFactory.getLogger(TopicKeyTest.class);

    private static final int MSG_LENGTH = 100;

    private static KafkaProducer<String, String> kafkaProducer;

    public static void main(String[] args) {

        List<ProducerRecord<String, String>> list = new ArrayList<>();
        for (int i = 0; i < MSG_LENGTH; i++) {
            list.add(new ProducerRecord<>("topic-without-key", null, null, String.valueOf(i)));
        }
        send(list);
        list.clear();


        for (int i = 0; i < MSG_LENGTH; i++) {
            list.add(new ProducerRecord<>("topic-with-integer-key", null, String.valueOf(i), String.valueOf(i)));
        }
        send(list);
        list.clear();


        for (int i = 0; i < MSG_LENGTH; i++) {
            list.add(new ProducerRecord<>("topic-with-integer-same-key", null, "10", String.valueOf(i)));
        }
        send(list);
        list.clear();

        kafkaProducer.close();
        System.out.println("消息发送完毕！");
    }

    static {
        Properties properties = initConfig();
        kafkaProducer = new KafkaProducer<>(properties);
    }

    private static Properties initConfig() {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094,localhost:9095");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }

    private static void send(List<ProducerRecord<String, String>> list) {
        for (ProducerRecord<String, String> producerRecord : list) {

            Future<RecordMetadata> future = kafkaProducer.send(producerRecord, (metadata, exception) -> {
                if (exception != null) {
                    log.info("key:{}, 异常{}", producerRecord.key(), exception.getMessage());
                    return;
                }
                if (metadata != null) {
                    log.info("key:{}, 分区:{}, 偏移量:{}:", producerRecord.key(), metadata.partition(),  metadata.offset());
                }
            });

            try {
                future.get(5, TimeUnit.MINUTES);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                e.printStackTrace();
            }
        }
    }
}
