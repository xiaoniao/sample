package com.example.profile.service.impl;

import com.example.profile.service.MessageService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Created by liuzz on 2018/04/18
 */
@Component
@Profile("test")
public class TestMessageServiceImpl implements MessageService {

    @Override
    public void send() {
        System.out.println("send test message");
    }
}
