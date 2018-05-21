package com.example.kafkaconsumer.stream;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Properties;

/**
 *
 * 统计单词stream
 *
 * Created by liuzz on 2018/05/21
 */
@Component
public class WordCountStream implements InitializingBean, DisposableBean {

    private Logger log = LoggerFactory.getLogger(WordCountStream.class);

    private KafkaStreams kafkaStreams;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("=========================== wordCount start ===========================");
        wordCount();
    }

    private void wordCount() {
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "KStream-word-count");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");


        KStreamBuilder kStreamBuilder = new KStreamBuilder();
        KStream<String, String> textLine = kStreamBuilder.stream("streams-word-count");
        KStream<String, String> filterReadLine = textLine.filter(new Predicate<String, String>() {
            @Override
            public boolean test(String key, String value) {
                System.out.println("filter");
                if (StringUtils.isEmpty(value)) {
                    return false;
                }
                return true;
            }
        });
        KStream<String, String> wordStream = filterReadLine.flatMapValues(new ValueMapper<String, Iterable<String>>() {
            @Override
            public Iterable<String> apply(String value) {
                return Arrays.asList(value.toLowerCase().split(","));
            }
        });
        KStream<String, String> wordPairs = wordStream.map(new KeyValueMapper<String, String, KeyValue<String, String>>() {
            @Override
            public KeyValue<String, String> apply(String key, String value) {
                return new KeyValue<>(value, value);
            }
        });
        KGroupedStream<String, String> wordGroup = wordPairs.groupByKey();

        KTable<String, Long> words = wordGroup.count();

        words.print();

        KafkaStreams kafkaStreams = new KafkaStreams(kStreamBuilder, properties);
        kafkaStreams.start();
    }

    @Override
    public void destroy() throws Exception {
        log.info("=========================== wordCount end ===========================");
        if (kafkaStreams != null) {
            kafkaStreams.close();
        }
    }
}
