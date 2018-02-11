package com.test.rabbitmq.config;

import com.test.rabbitmq.constant.QueueConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liuzz on 2018/02/07
 */
@Configuration
public class RabbitMqConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setConfirmCallback((CorrelationData correlationData, boolean ack, String cause) -> {
            if (ack) {
                System.out.println("消息id为: " + correlationData + "的消息，已经被ack成功");
            } else {
                System.out.println("消息id为: " + correlationData + "的消息，消息back，失败原因是：" + cause);
            }
        });
        return rabbitTemplate;
    }

    @Bean
    public Queue helloQueue() {
        return new Queue(QueueConstant.STUDENT_QUEUE);
    }

    @Bean
    public Queue cacheQueue() {
        return new Queue(QueueConstant.CACHE_QUEUE);
    }

    @Bean
    public Queue integralQueue() {
        return new Queue(QueueConstant.INTEGRAL_QUEUE);
    }

    @Bean
    public Queue uploadImageQueue() {
        return new Queue(QueueConstant.UPLOAD_IMAGE_QUEUE);
    }


    @Bean
    public Queue msgErrorInboxQueue() {
        return new Queue(QueueConstant.MSG_ERROR_INBOX_QUEUE);
    }

    @Bean
    public Queue msgInboxQueue() {
        return new Queue(QueueConstant.MSG_INBOX_QUEUE);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("spring-boot-fanout-exchange");
    }


    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("logs-exchange");
    }

    @Bean
    public Binding bindingCacheQueue(@Qualifier("cacheQueue") Queue queue, FanoutExchange exchange) {
        System.out.println("绑定:" + queue.getName());
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public Binding bindingIntegralQueue(@Qualifier("integralQueue") Queue queue, FanoutExchange exchange) {
        System.out.println("绑定:" + queue.getName());
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public Binding bindingUploadImageQueue(@Qualifier("uploadImageQueue") Queue queue, FanoutExchange exchange) {
        System.out.println("绑定:" + queue.getName());
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public Binding bindingMsgErrorInboxQueue(@Qualifier("msgErrorInboxQueue") Queue queue, TopicExchange exchange) {
        System.out.println("绑定:" + queue.getName());
        return BindingBuilder.bind(queue).to(exchange).with("error.msg-inbox");
    }

    @Bean
    public Binding bindingMsgInboxQueue(@Qualifier("msgInboxQueue") Queue queue, TopicExchange exchange) {
        System.out.println("绑定:" + queue.getName());
        return BindingBuilder.bind(queue).to(exchange).with("*.msg-inbox");
    }
}
