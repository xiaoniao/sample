package com.example.profile.service.impl;

import com.example.profile.service.MessageService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Created by liuzz on 2018/04/18
 */
@Component
@Profile("prod")
public class ProdMessageServiceImpl implements MessageService {

    @Override
    public void send() {
        System.out.println("send prod message");
    }
}
