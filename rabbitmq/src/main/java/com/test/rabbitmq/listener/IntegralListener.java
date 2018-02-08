package com.test.rabbitmq.listener;

import com.test.rabbitmq.constant.QueueConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by liuzz on 2018/02/07
 */
@Component
@RabbitListener(queues = QueueConstant.INTEGRAL_QUEUE)
public class IntegralListener {

    @RabbitHandler
    public void process(String msg) throws Exception {
        System.out.println("积分奖励" + msg);
    }
}
