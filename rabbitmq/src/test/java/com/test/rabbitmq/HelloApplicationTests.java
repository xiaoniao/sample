package com.test.rabbitmq;

import com.test.rabbitmq.sender.MessageSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by liuzz on 2018/02/07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloApplicationTests {

    @Autowired
    private MessageSender messageSender;

    @Test
    public void testSend() {
        messageSender.send("小明");
    }

    @Test
    public void testFanoutExchange() {
        messageSender.sendFanoutExchange("小明");
    }
}
