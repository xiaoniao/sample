package com.example.shardingtable.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by liuzz on 2018/06/05
 */
@Configuration
public class DataSourceConfig {
    private Logger log = LoggerFactory.getLogger(DataSourceConfig.class);


    @Bean
    public A showDataSource(DataSource dataSource) {
        log.info("*******************{}", dataSource.getClass());
        return new A();
    }

    public static class A {

    }
}
