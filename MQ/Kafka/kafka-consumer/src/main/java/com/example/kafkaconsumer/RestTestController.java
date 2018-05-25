package com.example.kafkaconsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuzz on 2018/05/17
 */
@RestController
@RequestMapping(value = "/consumer", method = RequestMethod.GET)
public class RestTestController {

    private Logger log = LoggerFactory.getLogger(RestTestController.class);

    private int count;

    /**
     * 处理高并发下的用户请求
     */
    @KafkaListener(topics = "${topic}")
    private void listen(ConsumerRecord<?, ?> cr) throws Exception {
        log.info("{}, {}", cr.toString(), count++);
        Thread.sleep(1000);
    }

    /**
     * ConsumerRecord(topic = topic-timestamp-create-time, partition = 0, offset = 0, CreateTime = 1526868542300,
     * serialized key size = -1, serialized value size = 3, headers = RecordHeaders(headers = [], isReadOnly = false), key = null, value = 111)
     */
    @KafkaListener(topics = "${topic.timestamp.createtime}")
    private void listen1(ConsumerRecord<?, ?> cr) throws Exception {
        log.info("{}, {}", cr.toString(), count++);
    }

    /**
     * ConsumerRecord(topic = topic-timestamp-log-append-time, partition = 1, offset = 0, LogAppendTime = 1526868505678,
     * serialized key size = -1, serialized value size = 3, headers = RecordHeaders(headers = [], isReadOnly = false), key = null, value = sss), 2
     */
    @KafkaListener(topics = "${topic.timestamp.logappendtime}")
    private void listen2(ConsumerRecord<?, ?> cr) throws Exception {
        log.info("{}, {}", cr.toString(), count++);
    }
}
