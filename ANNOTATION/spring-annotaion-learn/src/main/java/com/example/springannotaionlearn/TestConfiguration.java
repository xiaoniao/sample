package com.example.springannotaionlearn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liuzz on 2018/04/03
 *
 * <beans>
 */
@ComponentScan(basePackages = "com.example.springannotaionlearn")
@Configuration
public class TestConfiguration {

    @Autowired
    @Qualifier("testComponentBean-component")
    private TestComponentBean testComponentBean;

    public TestConfiguration() {
        System.out.println("TestConfiguration init .......");
    }

    /**
     * <bean>
     */
    @Bean
    public TestBean testBean() {
        System.out.println("new Bean TestBean");
        return new TestBean();
    }


    @Bean("testComponentBean-bean")
    public TestComponentBean testComponentBean() {
        System.out.println("new Bean TestComponentBean **** " + testComponentBean);
        return testComponentBean;
    }

    @Bean("testComponentBean-bean-standalone")
    public TestComponentBean testComponentBeanStandlone() {
        return new TestComponentBean();
    }
}
