package com.example.springaop;

import com.example.springaop.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by liuzz on 2018/06/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringAopApplication.class)
public class TestService {
    private Logger log = LoggerFactory.getLogger(TestService.class);

    @Autowired
    HelloService helloService;

    @Test
    public void testHelloService() {
        log.info(helloService.hello("a"));
    }
}
