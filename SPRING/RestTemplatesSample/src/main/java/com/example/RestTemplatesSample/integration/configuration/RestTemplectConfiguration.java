package com.example.RestTemplatesSample.integration.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by liuzz on 2018/05/04
 */
public class RestTemplectConfiguration {

    /**
     * Created by liuzz on 2018/05/04
     */
    @Configuration
    public static class RestTemplateConfiguration {

        @Bean
        public RestTemplate restTemplate(RestTemplateBuilder builder) {
            return builder
                    .setConnectTimeout(100000000)
                    .setReadTimeout(10000000)
                    .errorHandler(new GlobalRestErrorHandler()).defaultMessageConverters()
                    .build();
        }
    }
}
