package com.example.springinterfacelearn;

import com.example.springinterfacelearn.bean.DemoBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@ComponentScan("com.example.springinterfacelearn")
@Configuration
public class SpringInterfaceLearnApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringInterfaceLearnApplication.class);
        DemoBean demoBean = applicationContext.getBean(DemoBean.class);
        applicationContext.getAutowireCapableBeanFactory().destroyBean(demoBean);
    }
}
