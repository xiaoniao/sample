package com.example.kafkaapi.producer;

import com.example.kafkaapi.producer.model.StockQuotationInfo;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Created by liuzz on 2018/05/22
 */
public class QuotationProducerMultiThread {
    private Logger log = LoggerFactory.getLogger(QuotationProducerMultiThread.class);

    private static final int MSG_LENGTH = 100000;

    private static final String TOPIC = "stock-quotation-with-integer-key";

    private static KafkaProducer<String, String> kafkaProducer;

    private static Set<String> keys = new HashSet<>();

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

    private static StockQuotationInfo createStockQuotationlnfo() {
        StockQuotationInfo stockQuotationInfo = new StockQuotationInfo();
        Random r = new Random();
        Integer stockCode = 600100 + r.nextInt(10);

        stockQuotationInfo.setStockName("股票-" + stockCode);
        stockQuotationInfo.setStockCode(String.valueOf(stockCode));
        stockQuotationInfo.setCurrentPrice(1f);
        stockQuotationInfo.setHighPrice(1f);
        stockQuotationInfo.setLowPrice(1f);
        stockQuotationInfo.setOpenPrice(1f);
        stockQuotationInfo.setPreClosePrice(1f);
        stockQuotationInfo.setTradeTime(System.currentTimeMillis());
        return stockQuotationInfo;
    }

    public static void main(String[] args) {
        multiThread();
    }

    /**
     * 多线程(数据量大同时对顺序没有要求)
     */
    private static void multiThread() {
        Executor executor = Executors.newFixedThreadPool(5);

        CountDownLatch countDownLatch = new CountDownLatch(MSG_LENGTH);

        for (int i = 0; i < MSG_LENGTH; i++) {
            StockQuotationInfo stockQuotationInfo = createStockQuotationlnfo();

            Long timestamp = stockQuotationInfo.getTradeTime();
            String key = stockQuotationInfo.getStockCode();
            keys.add(key);
            String value = stockQuotationInfo.toString();
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(TOPIC, null, timestamp, String.valueOf(i), value);
            executor.execute(new KafkaProducerThread(countDownLatch, kafkaProducer, producerRecord));
        }

        for (String key : keys) {
            System.out.println(key);
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(MSG_LENGTH + "条消息发送完毕！");
        kafkaProducer.close();
    }
}
