package com.example.springannotaionlearn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class SpringAnnotaionLearnApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfiguration.class);

        TestBean testBean = applicationContext.getBean(TestBean.class);

        testBean.start();

        TestService testService = applicationContext.getBean(TestService.class);

        testService.start();


        // 验证是否是同一个对象
        TestComponentBean t1 = (TestComponentBean) applicationContext.getBean("testComponentBean-component");
        TestComponentBean t2 = (TestComponentBean) applicationContext.getBean("testComponentBean-bean");
        TestComponentBean t3 = (TestComponentBean) applicationContext.getBean("testComponentBean-bean-standalone");
        t1.setValue(1024);
        t1.printValue();t2.printValue();
        t3.printValue();
    }
}
