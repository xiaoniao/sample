package com.test.rabbitmq.listener;

import com.test.rabbitmq.constant.QueueNameConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by liuzz on 2018/02/07
 */
@Component
@RabbitListener(queues = QueueNameConstant.UPLOAD_IMAGE_QUEUE)
public class UploadImageRabbitListener {

    @RabbitHandler
    public void process(String msg) throws Exception {
        System.out.println("上传图片" + msg);
    }
}
