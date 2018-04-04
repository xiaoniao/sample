package com.example.springannotaionlearn.importAndimportSource.importSelector.config;

import com.example.springannotaionlearn.importAndimportSource.importSelector.service.impl.CoreContentServiceImpl;
import org.springframework.context.annotation.Bean;

/**
 * Created by liuzz on 2018/04/04
 */
public class CoreContentConfiguration {

    @Bean
    public CoreContentServiceImpl coreContentService() {
        return new CoreContentServiceImpl();
    }
}
