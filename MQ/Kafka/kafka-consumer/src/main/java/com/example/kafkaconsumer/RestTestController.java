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

    @KafkaListener(topics = "${topic}")
    private void listen(ConsumerRecord<?, ?> cr) throws Exception {
        log.info("{}, {}", cr.toString(), count++);
    }
}
