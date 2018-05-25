//package com.example.kafkaconsumer;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.Serde;
//import org.apache.kafka.common.serialization.Serdes;
//import org.apache.kafka.streams.KafkaStreams;
//import org.apache.kafka.streams.KeyValue;
//import org.apache.kafka.streams.StreamsConfig;
//import org.apache.kafka.streams.kstream.*;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import java.util.Arrays;
//import java.util.Properties;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by liuzz on 2018/05/21
// *
// * KStream
// * KTable
// *
// * 同级的API 可相互转换
// */
//@Component
//public class StreamSample implements InitializingBean {
//
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        new Thread(() -> kStream()).start();
//        new Thread(() -> kTable()).start();
//        new Thread(() -> kStreamJoin()).start();
//    }
//
//
//    /**
//     [KSTREAM-SOURCE-0000000000]: jetty, lllll
//     [KTABLE-SOURCE-0000000001]: jetty, (lllll<-null)
//     [KSTREAM-SOURCE-0000000000]: jetty, bbbb
//     [KTABLE-SOURCE-0000000001]: jetty, (bbbb<-null)
//     */
//
//    /**
//     * 流
//     */
//    public void kStream() {
//
//        Properties properties = new Properties();
//        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "KStream-test");
//        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        properties.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//        properties.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//
//
//        KStreamBuilder kStreamBuilder = new KStreamBuilder();
//        KStream<String, String> textLine = kStreamBuilder.stream("streams-foo");
//        textLine.print();
//
//
//        KafkaStreams kafkaStreams = new KafkaStreams(kStreamBuilder, properties);
//        kafkaStreams.start();
//
//        try {
//            Thread.sleep(500000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        kafkaStreams.close();
//    }
//
//
//    /**
//     * key 唯一 会覆盖，保存一份
//     */
//    public void kTable() {
//
//        Properties properties = new Properties();
//        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "KStream-test");
//        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        properties.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//        properties.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//
//
//        KStreamBuilder kStreamBuilder = new KStreamBuilder();
//        KTable<String , String> textLine = kStreamBuilder.table("streams-foo", "KStream-test");
//        textLine.print();
//
//        KafkaStreams kafkaStreams = new KafkaStreams(kStreamBuilder, properties);
//        kafkaStreams.start();
//
//        try {
//            Thread.sleep(500000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        kafkaStreams.close();
//    }
//
//
//    /**
//     * 连接操作 要保证执行连接操作的 KStreams 或 KTable 数据源的分区数相同，
//     * 同时生产者生产消息时分区分配策略相同，这样才能保证相同的键的消息分布在两个主题对应的分区相同。
//     *
//     *
//     *
//     * join(inner join)
//     * left join
//     * outer join
//     */
//    public void kStreamJoin() {
//        Serde<String> stringSerdes = Serdes.String();
//
//        KStreamBuilder kStreamBuilder = new KStreamBuilder();
//        KStream<String, String> leftStream = kStreamBuilder.stream(stringSerdes, stringSerdes, "left-source");
//        KStream<String, String> rightStream = kStreamBuilder.stream(stringSerdes, stringSerdes, "right-source");
//
//
//        KStream<String, String> joinStream = leftStream.join(rightStream, new ValueJoiner<String, String, String>() {
//            @Override
//            public String apply(String leftValue, String rightValue) {
//                return "leftValue: " + leftValue + ", rightValue: " + rightValue;
//            }
//        }, JoinWindows.of(TimeUnit.MINUTES.toMillis(5)), Serdes.String(), Serdes.String(), Serdes.String());
//
//        joinStream.print();
//
//
//        Properties properties = new Properties();
//        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "KStream-test");
//        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        properties.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//        properties.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//        KafkaStreams kafkaStreams = new KafkaStreams(kStreamBuilder, properties);
//        kafkaStreams.start();
//
//        try {
//            Thread.sleep(500000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        kafkaStreams.close();
//    }
//
//
//    public void kTableJoin() {
//        Serde<String> stringSerde = Serdes.String();
//        KStreamBuilder kStreamBuilder = new KStreamBuilder();
//
//
//        KTable<String, String> leftTable = kStreamBuilder.table(stringSerde, stringSerde, "left-source", "ktable-join-left");
//        KTable<String, String> rightTable = kStreamBuilder.table(stringSerde, stringSerde, "right-source", "ktable-join-right");
//
//        KTable<String, String> joinTable = leftTable.join(rightTable, new ValueJoiner<String, String, String>() {
//            @Override
//            public String apply(String leftValue, String rightValue) {
//                return "leftValue: " + leftValue + ", rightValue: " + rightValue;
//            }
//        });
//    }
//
//    public void kTableAndKStreamJoin() {
//        Serde<String> stringSerde = Serdes.String();
//        KStreamBuilder kStreamBuilder = new KStreamBuilder();
//
//
//        KStream<String, String> leftStream = kStreamBuilder.stream(stringSerde, stringSerde, "left-source");
//        KTable<String, String> rightTable = kStreamBuilder.table(stringSerde, stringSerde, "right-source", "ktable-join-right");
//
//
//        leftStream.leftJoin(rightTable, new ValueJoiner<String, String, String>() {
//            @Override
//            public String apply(String leftValue, String rightValue) {
//                return "leftValue: " + leftValue + ", rightValue: " + rightValue;
//            }
//        }, Serdes.String(), Serdes.String());
//    }
//
//
////    public void wordCount() {
////        Properties properties = new Properties();
////        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "KStream-test");
////        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
////        properties.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
////        properties.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
////        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
////
////
////        KStreamBuilder kStreamBuilder = new KStreamBuilder();
////        KStream<String, String> textLine = kStreamBuilder.stream("streams-foo");
////        KStream<String, String> filterReadLine = textLine.filter(new Predicate<String, String>() {
////            @Override
////            public boolean test(String key, String value) {
////                if (StringUtils.isEmpty(value)) {
////                    return false;
////                }
////                return true;
////            }
////        });
////        KStream<String, String> wordStream = filterReadLine.flatMapValues(new ValueMapper<String, Iterable<String>>() {
////            @Override
////            public Iterable<String> apply(String value) {
////                return Arrays.asList(value.toLowerCase().split(","));
////            }
////        });
////        KStream<String, String> wordPairs = wordStream.map(new KeyValueMapper<String, String, KeyValue<String, String>>() {
////            @Override
////            public KeyValue<String, String> apply(String key, String value) {
////                return new KeyValue<>(value, value);
////            }
////        });
////        KGroupedStream<String, String> wordGroup = wordPairs.groupByKey();
////
////        KTable<String, Long> words = wordGroup.count("");
////
////
////    }
//}
