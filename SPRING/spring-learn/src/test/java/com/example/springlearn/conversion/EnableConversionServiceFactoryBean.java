package com.example.springlearn.conversion;

import java.util.HashSet;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by liuzz on 2018/04/26
 */
@Configuration
public class EnableConversionServiceFactoryBean {

    @Bean
    public ConversionServiceFactoryBean conversionServiceFactoryBean() {
        System.out.println("添加自定义Converter");
        /**
         * 向 ConversionService 中添加 Converter.
         */
        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
        Set<Converter> converters = new HashSet<>();
        converters.add(new MyProductConverter2());
        conversionServiceFactoryBean.setConverters(converters);
        return conversionServiceFactoryBean;
    }
}
