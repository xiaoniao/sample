package com.example.kafkaapi.consumer;


import kafka.api.FetchRequest;
import kafka.api.FetchRequestBuilder;
import kafka.api.PartitionOffsetRequestInfo;
import kafka.common.ErrorMapping;
import kafka.common.TopicAndPartition;
import kafka.javaapi.*;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.message.MessageAndOffset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.math.Ordering;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzz on 2018/05/22
 *
 *
 * consumer offsets 消费偏移量的分配算法
 *
 * 通过${group.id}的 hashcode 值与 ${offsets.topic.num.partition} 取模的方式来确定某个消费组己消费的偏移量保存到该主题的哪个分区中。
 *
 *
 * 旧版 [scala]
 *
 *      低级 SimpleConsumer
 *
 *      高级 ZooKeeperConsumerConnector
 *
 * 新版 [Java]
 *
 *      KafkaConsumer
 */
public class KafkaSimpleConsumer {

    private Logger log = LoggerFactory.getLogger(KafkaSimpleConsumer.class);

    private static final String BROKER_LIST = "localhost:9092,localhost:9093,localhost:9094,localhost:9095";

    private static final int TIME_OUT = 60 * 1000;

    private static final int BUGGER_SIZE = 1024 * 1024;

    private static final int FETCH_SIZE = 100000;

    private static final int MAX_ERROR_SIZE = 3;

    public static void main(String[] args) {
        PartitionMetadata partitionMetadata = KafkaSimpleConsumer.fetchPartitionMetadata(Arrays.asList(BROKER_LIST.split(",")), "stock-quotation-with-key", 1);

        System.out.println(partitionMetadata.toString());
    }

    /**
     * 获取分区元数据
     */
    private static PartitionMetadata fetchPartitionMetadata(List<String> brokerList, String topic, int partitionId) {
        for (String broker : brokerList) {

            String host = broker.split(":")[0];
            Integer port = Integer.valueOf(broker.split(":")[1]);

            SimpleConsumer simpleConsumer = new SimpleConsumer(host, port, TIME_OUT, BUGGER_SIZE, "fetch-metadata");

            TopicMetadataRequest topicMetadataRequest = new TopicMetadataRequest(Arrays.asList(topic));

            TopicMetadataResponse topicMetadataResponse;
            try {
                topicMetadataResponse = simpleConsumer.send(topicMetadataRequest);
            } catch (Exception e) {
                continue;
            }
            simpleConsumer.close();

            List<TopicMetadata> topicMetadataList = topicMetadataResponse.topicsMetadata();

            for (TopicMetadata topicMetadata : topicMetadataList) {

                List<PartitionMetadata> partitionMetadataList = topicMetadata.partitionsMetadata();
                for (PartitionMetadata partitionMetadata : partitionMetadataList) {

                    if (partitionMetadata.partitionId() == partitionId) {
                        return partitionMetadata;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 获取消息偏移量
     */
    private long getLastOffset(SimpleConsumer consumer, String topic, int partition, long beginTime, String clientId) {
        TopicAndPartition topicAndPartition = new TopicAndPartition(topic, partition);
        Map<TopicAndPartition, PartitionOffsetRequestInfo> requestInfo = new HashMap<>();

        requestInfo.put(topicAndPartition, new PartitionOffsetRequestInfo(beginTime, 1));

        OffsetRequest offsetRequest = new OffsetRequest(requestInfo, kafka.api.OffsetRequest.CurrentVersion(), clientId);

        OffsetResponse response = consumer.getOffsetsBefore(offsetRequest);

        if (response.hasError()) {
            return -1;
        }

        long[] offsets = response.offsets(topic, partition);
        if (offsets == null || offsets.length == 0) {
            return -1;
        }

        return offsets[0];
    }

    public void consume(List<String> brokerList, String topic, int partitionId) {


        SimpleConsumer consumer = null;


        PartitionMetadata metadata = fetchPartitionMetadata(brokerList, topic, partitionId);

        if (metadata == null) {
            return;
        }

        if (metadata.leader() == null) {
            return;
        }

        String leadBroker = metadata.leader().host();
        int leaderPort = metadata.leader().port();
        String clientId = "client-" + topic + "-" + partitionId;

        consumer = new SimpleConsumer(leadBroker, leaderPort, TIME_OUT, BUGGER_SIZE, clientId);

        long lastOffset = getLastOffset(consumer, topic, partitionId, kafka.api.OffsetRequest.EarliestTime(), clientId);


        int errorNum = 0;

        while (lastOffset > 1) {
            if (consumer == null) {
                consumer = new SimpleConsumer(leadBroker, leaderPort, TIME_OUT, BUGGER_SIZE, clientId);
            }

            FetchRequest fetchRequest = new FetchRequestBuilder().clientId(clientId).addFetch(topic, partitionId, lastOffset, FETCH_SIZE).build();

            FetchResponse fetchResponse = consumer.fetch(fetchRequest);

            if (fetchResponse.hasError()) {
                errorNum++;

                if (errorNum > MAX_ERROR_SIZE) {
                    break;
                }

                short errorCode = fetchResponse.errorCode(topic, partitionId);

                if (ErrorMapping.OffsetOutOfRangeCode() == errorCode) {

                    lastOffset = getLastOffset(consumer, topic, partitionId, kafka.api.OffsetRequest.LatestTime(), clientId);
                    continue;

                } else if (ErrorMapping.OffsetsLoadInProgressCode() == errorCode) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;

                } else {
                    consumer.close();
                    consumer = null;
                    continue;
                }
            } else {
                errorNum = 0;



                long fetchNum = 0;

                for (MessageAndOffset messageAndOffset : fetchResponse.messageSet(topic, partitionId)) {
                    long currentOffset = messageAndOffset.offset();
                    if (currentOffset < lastOffset) {
                        continue;
                    }

                    lastOffset = messageAndOffset.nextOffset();
                    ByteBuffer payload = messageAndOffset.message().payload();

                    byte[] bytes = new byte[payload.limit()];
                    payload.get(bytes);

                    try {
                        System.out.println(new String(bytes, "UTF-8") + "" + messageAndOffset.offset());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    fetchNum++;

                }

                if (fetchNum == 0) {

                }


            }

        }

    }





}
