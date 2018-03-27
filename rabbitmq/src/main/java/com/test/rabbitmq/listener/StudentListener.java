package com.test.rabbitmq.listener;

import com.test.rabbitmq.constant.QueueConstant;
import java.util.concurrent.CountDownLatch;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by liuzz on 2018/02/07
 */
@Component
@RabbitListener(queues = QueueConstant.STUDENT_QUEUE)
public class StudentListener {

    private CountDownLatch latch = new CountDownLatch(1);

    @RabbitHandler
    public void process(String msg) throws Exception {
        System.out.println("学生" + msg);
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
