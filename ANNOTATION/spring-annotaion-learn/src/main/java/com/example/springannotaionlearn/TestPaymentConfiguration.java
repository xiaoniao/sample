package com.example.springannotaionlearn;

import org.springframework.context.annotation.Configuration;

/**
 * Created by liuzz on 2018/04/03
 *
 * Configuration 本身也继承了 Component ，也会被扫描成 Bean
 *
 */
@Configuration
public class TestPaymentConfiguration {

    public TestPaymentConfiguration() {
        System.out.println("TestPaymentConfiguration init ......");
    }
}
