package com.test.rabbitmq.listener;

import com.test.rabbitmq.constant.QueueConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by liuzz on 2018/02/07
 */
@Component
@RabbitListener(queues = QueueConstant.MSG_ERROR_INBOX_QUEUE)
public class LogErrorListener {

    @RabbitHandler
    public void process(String msg) throws Exception {
        System.out.println("错误日志" + msg);
    }
}
