package com.example.springlearn;

import java.util.Properties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liuzz on 2018/04/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringLearnApplication.class)
public class DefaultConversionServiceTest {

    @Test
    public void test() {
        DefaultConversionService defaultConversionService = new DefaultConversionService();
        Properties properties = defaultConversionService.convert("name:nana\nage:23", Properties.class);
        System.out.println("name : " + properties.getProperty("name"));
        System.out.println("age : " + properties.getProperty("age"));
    }
}
