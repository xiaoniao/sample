package com.example.testingPerformance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuzz on 2018/06/28
 */
@RestController
@RequestMapping("/")
public class SampleController {
    private Logger log = LoggerFactory.getLogger(SampleController.class);

    private volatile int counter;

    @RequestMapping("/sample")
    public String sample() {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        log.info("{}", counter++);
        return "ss";
    }
}
