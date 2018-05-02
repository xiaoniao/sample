//package com.example.value;
//
//import java.util.HashSet;
//import java.util.Set;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.support.ConversionServiceFactoryBean;
//import org.springframework.core.convert.converter.GenericConverter;
//
///**
// * Created by liuzz on 2018/04/26
// */
//@Configuration
//public class DiyValueConfig {
//
//    @Bean("conversionService")
//    public ConversionServiceFactoryBean getConversionService() {
//        System.out.println("添加自定义Converter");
//        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
//        Set<GenericConverter> converters = new HashSet<>();
//        converters.add(new DiyGenericConverter());
//        conversionServiceFactoryBean.setConverters(converters);
//        return conversionServiceFactoryBean;
//    }
//}
