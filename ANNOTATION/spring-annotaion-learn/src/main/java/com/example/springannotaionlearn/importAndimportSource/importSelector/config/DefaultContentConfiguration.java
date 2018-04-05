package com.example.springannotaionlearn.importAndimportSource.importSelector.config;

import com.example.springannotaionlearn.importAndimportSource.importSelector.service.impl.DefaultContentServiceImpl;
import org.springframework.context.annotation.Bean;

/**
 * Created by liuzz on 2018/04/04
 */
public class DefaultContentConfiguration {

    @Bean
    public DefaultContentServiceImpl defaultContentService() {
        return new DefaultContentServiceImpl();
    }
}