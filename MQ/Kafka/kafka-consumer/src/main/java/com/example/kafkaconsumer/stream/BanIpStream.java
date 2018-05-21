package com.example.kafkaconsumer.stream;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.ProcessorSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Created by liuzz on 2018/05/21
 */
@Component
public class BanIpStream implements InitializingBean, DisposableBean {

    private Logger log = LoggerFactory.getLogger(BanIpStream.class);

    private KafkaStreams kafkaStreams;

    @Override
    public void afterPropertiesSet() throws Exception {
        banIp();
    }

    public static class IpBlackListProcessor implements Processor<Windowed<String>, Long> {

        @Override
        public void init(ProcessorContext context) {

        }

        @Override
        public void process(Windowed<String> key, Long value) {
            System.out.println("ip:" + key.key() + "被加入到黑名单，请求次数为" + value);
        }

        @Override
        public void punctuate(long timestamp) {

        }

        @Override
        public void close() {

        }
    }

    private void banIp() {
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "KStream-ban-ip");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KStreamBuilder kStreamBuilder = new KStreamBuilder();
        KStream<String, String> asscessLog = kStreamBuilder.stream("streams-ban-ip");


        asscessLog
                .map(new KeyValueMapper<String, String, KeyValue<String, String>>() {
                    @Override
                    public KeyValue<String, String> apply(String key, String value) {
                        return new KeyValue<>(key, value);
                    }
                })
                .groupByKey()
                .count(TimeWindows.of(60 * 1000L).advanceBy(60 * 1000L), "access-count")
                .toStream()
                .filter(new Predicate<Windowed<String>, Long>() {
                    @Override
                    public boolean test(Windowed<String> key, Long value) {
                        if (value != null && value.longValue() >= 2) {
                            return true;
                        }
                        return false;
                    }
                })
                .process(new ProcessorSupplier<Windowed<String>, Long>() {
                    @Override
                    public Processor<Windowed<String>, Long> get() {
                        return new IpBlackListProcessor();
                    }
                }, "access-count");


        KafkaStreams kafkaStreams = new KafkaStreams(kStreamBuilder, properties);
        kafkaStreams.start();
    }

    @Override
    public void destroy() throws Exception {
        log.info("=========================== maxValue end ===========================");
        if (kafkaStreams != null) {
            kafkaStreams.close();
        }
    }
}
