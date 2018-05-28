package com.example.kafkaapi.producer;

import com.example.kafkaapi.producer.model.StockQuotationInfo;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by liuzz on 2018/05/22
 */
public class QuotationProducerSingleThread {
    private Logger log = LoggerFactory.getLogger(QuotationProducerSingleThread.class);

    private static final int MSG_SIZE = 100;

    private static final String TOPIC = "stock-quotation";

    private static KafkaProducer<String, String> kafkaProducer;

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
         singleThread();
    }

    /**
     * 单线程
     */
    private static void singleThread() {
        for (int i = 0; i < MSG_SIZE; i++) {

            StockQuotationInfo stockQuotationInfo = new StockQuotationInfo();

            Integer partition = null;
            Long timestamp = stockQuotationInfo.getTradeTime();
            String key = stockQuotationInfo.getStockCode();
            String value = stockQuotationInfo.toString();

            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(TOPIC, partition, timestamp, key, value);


            /**
             * 生产者发送消息实质分两个阶段
             *
             * 1、第一阶段是将消息发送到消息缓冲区
             * 2、第二阶段是一个Sender 线程负责将缓冲区的消息发送到代理，执行真正的1/0 操作
             *
             *
             * 默认异步发送消息
             * 在第一阶段执行完后就返回一个Future 对象，根据对Future对象处理方式的不同， KafkaProducer 支持两种发送消息方式
             * 不管 future 异步
             * future.get(5, TimeUnit.SECONDS); 同步
             */
            Future<RecordMetadata> future = kafkaProducer.send(producerRecord, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (exception != null) {
                        System.out.println("异常");
                        return;
                    }
                    if (metadata != null) {
                        System.out.println("partition:" + metadata.partition() + " offset:" + metadata.offset());
                    }
                }
            });

            try {
                future.get(5, TimeUnit.SECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                e.printStackTrace();
            }
            System.out.println(i + "发送成功");
        }

        kafkaProducer.close();
    }
}
