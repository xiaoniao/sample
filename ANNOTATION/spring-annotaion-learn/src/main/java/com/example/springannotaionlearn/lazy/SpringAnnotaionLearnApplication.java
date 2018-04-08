package com.example.springannotaionlearn.lazy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class SpringAnnotaionLearnApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(LazyConfig.class);
//        LazyBean lazyBean = applicationContext.getBean(LazyBean.class);
    }
}
