package com.test.rabbitmq.config;

import com.test.rabbitmq.constant.QueueNameConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liuzz on 2018/05/29
 */
@Configuration
public class QueueConfiguration {

    @Bean
    public Queue helloQueue() {
        return new Queue(QueueNameConstant.STUDENT_QUEUE);
    }

    @Bean
    public Queue cacheQueue() {
        return new Queue(QueueNameConstant.CACHE_QUEUE);
    }

    @Bean
    public Queue integralQueue() {
        return new Queue(QueueNameConstant.INTEGRAL_QUEUE);
    }

    @Bean
    public Queue uploadImageQueue() {
        return new Queue(QueueNameConstant.UPLOAD_IMAGE_QUEUE);
    }

    @Bean
    public Queue msgErrorInboxQueue() {
        return new Queue(QueueNameConstant.MSG_ERROR_INBOX_QUEUE);
    }

    @Bean
    public Queue msgInboxQueue() {
        return new Queue(QueueNameConstant.MSG_INBOX_QUEUE);
    }

}
