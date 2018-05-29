package com.test.rabbitmq.sender;

import com.test.rabbitmq.constant.QueueNameConstant;
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
        amqpTemplate.convertAndSend(QueueNameConstant.STUDENT_QUEUE, msg);
    }

    public void sendFanoutExchange(String msg) {
        amqpTemplate.convertAndSend("spring-boot-fanout-exchange", "", msg);
    }

    public void sendTopicExchange(String msg) {
        amqpTemplate.convertAndSend("logs-exchange", "error.msg-inbox", msg);
    }
}
