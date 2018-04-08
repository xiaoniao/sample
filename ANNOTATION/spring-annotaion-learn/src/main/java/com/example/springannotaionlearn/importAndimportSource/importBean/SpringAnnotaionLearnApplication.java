package com.example.springannotaionlearn.importAndimportSource.importBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


//@Import(DemoService.class)
public class SpringAnnotaionLearnApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.example.springannotaionlearn");

        DemoConfig demoConfig = applicationContext.getBean(DemoConfig.class);
        demoConfig.print();

        DemoService demoService = applicationContext.getBean(DemoService.class);
        demoService.print();
    }

}
