package com.example.kafkaproducer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuzz on 2018/05/17
 */
@RestController
@RequestMapping(value = "/producer", method = RequestMethod.GET)
public class RestTestController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("/send/{topic}/{key}/{data}")
    public String getFile(@PathVariable String topic, @PathVariable String key, @PathVariable String data) {
        ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(topic, key, data);
        return "success";
    }
}
