//package com.example.kafkalearn.stream;
//
//import org.apache.kafka.common.serialization.Serdes;
//import org.apache.kafka.streams.KeyValue;
//import org.apache.kafka.streams.StreamsBuilder;
//import org.apache.kafka.streams.StreamsConfig;
//import org.apache.kafka.streams.kstream.KStream;
//import org.apache.kafka.streams.kstream.TimeWindows;
//import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.annotation.EnableKafkaStreams;
//import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by liuzz on 2018/05/16
// */
//@Configuration
//@EnableKafka
//@EnableKafkaStreams
//public class KafkaStreamsConfiguration {
//
//    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
//    public StreamsConfig kStreamsConfigs() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "testStreams");
//        props.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.Integer().getClass().getName());
//        props.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
//        props.put(StreamsConfig.TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class.getName());
//        return new StreamsConfig(props);
//    }
//
//    @Bean
//    public KStream<Integer, String> kStream(StreamsBuilder kStreamBuilder) {
//        KStream<Integer, String> stream = kStreamBuilder.stream("streamingTopic1");
//        stream
//                .mapValues(String::toUpperCase)
//                .groupByKey()
//                .reduce((String value1, String value2) -> value1 + value2,
//                        TimeWindows.of(1000),
//                        "windowStore")
//                .toStream()
//                .map((windowedId, value) -> new KeyValue<>(windowedId.key(), value))
//                .filter((i, s) -> s.length() > 40)
//                .to("streamingTopic2");
//
//        stream.print();
//
//        return stream;
//    }
//
//
//}
