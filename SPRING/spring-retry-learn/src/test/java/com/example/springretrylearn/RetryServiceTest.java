package com.example.springretrylearn;

import com.example.springretrylearn.service.RetryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by liuzhuang on 5/9/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringRetryLearnApplication.class)
public class RetryServiceTest {

    @Autowired
    private RetryService retryService;

    @Test
    public void testRetry() {
        retryService.queryUserName("10001");
    }

}
