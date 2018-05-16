//package com.example.kafkalearn.stream.plain;
//
//import org.apache.kafka.common.serialization.Serdes;
//import org.apache.kafka.streams.Consumed;
//import org.apache.kafka.streams.StreamsBuilder;
//import org.apache.kafka.streams.kstream.KStream;
//import org.apache.kafka.streams.kstream.KTable;
//import org.apache.kafka.streams.kstream.Produced;
//
//import java.util.Arrays;
//
///**
// * Created by liuzz on 2018/05/16
// */
//public class PlainJavaStreamSample {
//
//
//    public static void main(String[] args) {
//        StreamsBuilder builder = new StreamsBuilder();
//
//        Consumed<String, Long> consumed = Consumed.with(Serdes.String(), Serdes.Long());
//
//        KStream<String, Long> textLines = builder.stream("http_topic", consumed);
//
//        KTable<String, Long> wordCounts = textLines
//                .flatMapValues(value -> Arrays.asList(String.valueOf(value).toLowerCase().split("\\W+")))
//                .groupBy((key, value) -> value)
//                .count();
//
//        wordCounts
//                .toStream()
//                .to("streams-wordcount-output", Produced.with(Serdes.String(), Serdes.Long()));
//    }
//
//}
