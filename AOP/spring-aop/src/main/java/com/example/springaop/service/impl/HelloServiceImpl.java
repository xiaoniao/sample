package com.example.springaop.service.impl;

import com.example.springaop.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * Created by liuzz on 2018/06/13
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String arg) {
        return arg;
    }
}
