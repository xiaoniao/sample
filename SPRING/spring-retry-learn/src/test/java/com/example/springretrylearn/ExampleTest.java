package com.example.springretrylearn;

import com.example.springretrylearn.model.Foo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by liuzz on 2018/05/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringRetryLearnApplication.class)
public class ExampleTest {

    /**
     * RetryOperations
     *      - RetryTemplate
     *
     * RetryCallback
     *
     * RecoveryCallback
     *
     *
     */

    @Test
    public void basicUse() {
        RetryTemplate retryTemplate = new RetryTemplate();

        RetryCallback retryCallback = (RetryContext retryContext) -> {
            int retryCount = retryContext.getRetryCount();
            System.out.println("retryCount : " + retryCount);
            throw new MyTimeOutException();
        };

        RecoveryCallback<Foo> recoveryCallback = (RetryContext retryContext) -> {
            System.out.println("recover");
            return new Foo();
        };

        Foo result = retryTemplate.execute(retryCallback, recoveryCallback);
        System.out.println(result);
    }

}
