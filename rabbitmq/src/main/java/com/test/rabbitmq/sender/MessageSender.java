package com.test.rabbitmq.sender;

import com.test.rabbitmq.constant.QueueConstant;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by liuzz on 2018/02/07
 */
@Component
public class MessageSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String msg) {
        amqpTemplate.convertAndSend(QueueConstant.STUDENT_QUEUE, msg);
    }

    public void sendFanoutExchange(String msg) {
        amqpTemplate.convertAndSend("spring-boot-fanout-exchange", "", msg);
    }
}
