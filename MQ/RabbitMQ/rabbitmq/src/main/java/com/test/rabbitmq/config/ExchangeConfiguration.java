package com.test.rabbitmq.config;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liuzz on 2018/05/29
 */
@Configuration
public class ExchangeConfiguration {

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("spring-boot-fanout-exchange");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("logs-exchange");
    }
}
