package com.example.mineStarter;

import com.example.third.library.PayService;
import com.example.third.library.impl.PayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liuzz on 2018/04/02
 */
@Configuration
@ConditionalOnClass(PayService.class)
@EnableConfigurationProperties(ExampleServiceProperties.class)
public class ExampleAutoConfigure {

    @Autowired
    private ExampleServiceProperties properties;

    public ExampleAutoConfigure() {
        System.out.println("new ExampleAutoConfigure()");
    }

    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "example.service", value = "enabled", havingValue = "true")
    @Bean
    public ExampleService exampleService() {
        System.out.println("new ExampleService(\"" + properties.getPrefix() + "\", \"" + properties.getSuffix() + "\")");
        return new ExampleService(properties.getPrefix(), properties.getSuffix());
    }

    @Bean
    public PayService payService() {
        return new PayServiceImpl();
    }
}
