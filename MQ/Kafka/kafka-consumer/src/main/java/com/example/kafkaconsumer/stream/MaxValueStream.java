//package com.example.kafkaconsumer.stream;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.Serdes;
//import org.apache.kafka.streams.KafkaStreams;
//import org.apache.kafka.streams.KeyValue;
//import org.apache.kafka.streams.StreamsConfig;
//import org.apache.kafka.streams.kstream.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import java.util.Arrays;
//import java.util.Properties;
//
///**
// * Created by liuzz on 2018/05/21
// */
//@Component
//public class MaxValueStream implements InitializingBean, DisposableBean {
//
//    private Logger log = LoggerFactory.getLogger(WordCountStream.class);
//
//    private KafkaStreams kafkaStreams;
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        log.info("=========================== maxValue start ===========================");
//        maxValue();
//    }
//
//    private void maxValue() {
//        Properties properties = new Properties();
//        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "KStream-value-max");
//        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        properties.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//        properties.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//
//
//        KStreamBuilder kStreamBuilder = new KStreamBuilder();
//        KStream<String, String> textLine = kStreamBuilder.stream("streams-value-max");
//        KStream<String, String> filterReadLine = textLine.filter(new Predicate<String, String>() {
//            @Override
//            public boolean test(String key, String value) {
//                System.out.println("filter");
//                if (StringUtils.isEmpty(value)) {
//                    return false;
//                }
//                return true;
//            }
//        });
//
//        filterReadLine.map(new KeyValueMapper<String, String, KeyValue<String, Integer>>() {
//            @Override
//            public KeyValue<String, Integer> apply(String key, String value) {
//                return new KeyValue<>(key, Integer.valueOf(value));
//            }
//        }).groupByKey(Serdes.String(), Serdes.Integer()).aggregate(new Initializer<Integer>() {
//            @Override
//            public Integer apply() {
//                return Integer.MIN_VALUE;
//            }
//        }, new Aggregator<String, Integer, Integer>() {
//            @Override
//            public Integer apply(String key, Integer value, Integer aggregate) {
//                return value > aggregate ? value : aggregate;
//            }
//        }, TimeWindows.of(60 * 1000L).advanceBy(60 * 1000L), Serdes.Integer(), "max");
//
//        filterReadLine.print();
//
//        KafkaStreams kafkaStreams = new KafkaStreams(kStreamBuilder, properties);
//        kafkaStreams.start();
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        log.info("=========================== maxValue end ===========================");
//        if (kafkaStreams != null) {
//            kafkaStreams.close();
//        }
//    }
//}
