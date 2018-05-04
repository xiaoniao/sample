package com.example.javaLearn.manager;

import com.example.javaLearn.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by liuzz on 2018/05/04
 */
@Component
public class TestManager {

    @Autowired
    private TestService testService;

    public void testWithException() throws Exception {
        testService.testWithException();
    }

    public void testWithRuntimeException() {
        testService.testWithRuntimeException();
    }

    public void testWithBizException() {
        testService.testWithBizException();
    }
}
