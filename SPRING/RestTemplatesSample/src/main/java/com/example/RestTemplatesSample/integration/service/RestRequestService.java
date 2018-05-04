package com.example.RestTemplatesSample.integration.service;

import com.example.RestTemplatesSample.integration.model.Quote;
import com.example.RestTemplatesSample.integration.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by liuzz on 2018/05/04
 */
@Component
public class RestRequestService {

    @Autowired
    private RestTemplate restTemplate;

    public Result queryQuote() {
        // http://gturnquist-quoters.cfapps.io/api/random
        return Result.wrapSuccessfulResult(restTemplate.getForObject("http://localhost:8080/test", Quote.class));
    }
}