package com.example.hystrixSample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuzz on 2018/05/03
 */
@RestController
public class SampleController {
    private Logger log = LoggerFactory.getLogger(SampleController.class);

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/product")
    public String product() {
        log.info("product A 4000");
        sleep(4000);
        log.info("product B");
        return "product";
    }

    @RequestMapping("/order")
    public String order() {
        log.info("order A 5000");
        sleep(5000);
        log.info("order B");
        return "order";
    }

    @RequestMapping("/cart")
    public String cart() {
        log.info("cart A 10000");
        sleep(10000);
        log.info("cart B");
        return "cart";
    }
}
