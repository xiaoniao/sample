package com.example.springretrylearn.service.impl;

import com.example.springretrylearn.exception.TimeOutException;
import com.example.springretrylearn.service.RetryService;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * Created by liuzhuang on 5/9/18.
 */
@Service
public class RetryServiceImpl implements RetryService {

    @Retryable(value = {TimeOutException.class}, maxAttempts = 5, backoff = @Backoff(delay = 500l, multiplier = 1))
    @Override
    public String queryUserName(String userNo) {
        System.out.println(userNo);
        throw new TimeOutException();
    }

    @Recover
    public void recover(TimeOutException timeOutException) {
        System.out.println("recover....");
    }
}
