package com.example.springannotaionlearn.importAndimportSource.importSelector.config;

import com.example.springannotaionlearn.importAndimportSource.importSelector.service.impl.SimpleContentServiceImpl;
import org.springframework.context.annotation.Bean;

/**
 * Created by liuzz on 2018/04/04
 */
public class SimpleContentConfiguration {

    @Bean
    public SimpleContentServiceImpl simpleContentService() {
        return new SimpleContentServiceImpl();
    }
}
