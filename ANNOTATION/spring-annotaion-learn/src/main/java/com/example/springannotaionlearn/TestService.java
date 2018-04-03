package com.example.springannotaionlearn;

import org.springframework.stereotype.Component;

/**
 * Created by liuzz on 2018/04/03
 *
 * 需要结合 @ComponentScan 一起使用
 */
@Component
public class TestService {

    public void start() {
        System.out.println("TestService start ......");
    }

}
