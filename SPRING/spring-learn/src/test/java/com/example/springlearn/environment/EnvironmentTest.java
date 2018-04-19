package com.example.springlearn.environment;

import com.example.springlearn.SpringLearnApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liuzz on 2018/04/19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringLearnApplication.class)
public class EnvironmentTest {

    @Value("${HOME}")
    private String home;

    @Autowired
    private Environment environment;

    @Test
    public void testValueSystemProperties() {
        System.out.println("home : " + home);
    }

    @Test
    public void testProperties() {
        System.out.println(environment.getProperty("ages"));
        System.out.println(environment.getProperty("ages", java.util.List.class));
        System.out.println(environment.getProperty("age", Integer.class));
    }
}
