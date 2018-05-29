package com.test.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * 绑定队列(queue)到交换器(exchange)上
 *
 * Created by liuzz on 2018/05/29
 */
@Configuration
public class BindingConfiguration {

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
