package com.example.druidSample.controller;

import com.example.druidSample.service.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuzz on 2018/05/03
 */
@RestController
public class TestController {

    @Autowired
    private TestRepository testRepository;

    @RequestMapping("/test")
    public String test() {
        return testRepository.test();
    }
}
