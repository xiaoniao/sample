package com.example.springannotaionlearn.importAndimportSource.importSelector;

import com.example.springannotaionlearn.importAndimportSource.importSelector.anno.EnableContentService;
import com.example.springannotaionlearn.importAndimportSource.importSelector.service.impl.CoreContentServiceImpl;
import com.example.springannotaionlearn.importAndimportSource.importSelector.service.impl.SimpleContentServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
// @EnableContentService()
@EnableContentService(policy = "core")
public class SpringAnnotaionLearnApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.example.springannotaionlearn");

        SimpleContentServiceImpl simpleContentServiceImpl = applicationContext.getBean(SimpleContentServiceImpl.class);

        simpleContentServiceImpl.doSomething();

        CoreContentServiceImpl contentService = applicationContext.getBean(CoreContentServiceImpl.class);

        contentService.doSomething();
    }
}
