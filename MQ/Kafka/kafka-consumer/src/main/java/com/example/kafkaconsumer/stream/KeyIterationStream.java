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

import java.util.Properties;

/**
 * Created by liuzz on 2018/05/21
 */
@Component
public class KeyIterationStream implements InitializingBean, DisposableBean {

    private Logger log = LoggerFactory.getLogger(KeyIterationStream.class);

    private KafkaStreams kafkaStreams;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("=========================== Key Iterator start ===========================");
        maxValue();
    }

    private void maxValue() {
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "KStream-key-iterator");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");


        KStreamBuilder kStreamBuilder = new KStreamBuilder();
        KStream<String, String> keyStrem = kStreamBuilder.stream("stock-quotation-with-key");


        KTable<String, Long> kTable =
                keyStrem
                        .map((String key, String value) -> new KeyValue<>(key, Integer.valueOf(value)))
                        .groupByKey().count();

        kTable.print();

        KafkaStreams kafkaStreams = new KafkaStreams(kStreamBuilder, properties);
        kafkaStreams.start();
    }

    @Override
    public void destroy() throws Exception {
        log.info("=========================== Key Iterator end ===========================");
        if (kafkaStreams != null) {
            kafkaStreams.close();
        }
    }
}
