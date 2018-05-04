package com.example.RestTemplatesSample;

import com.example.RestTemplatesSample.integration.model.Quote;
import com.example.RestTemplatesSample.integration.service.RestRequestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * Created by liuzz on 2018/05/04
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestTemplatesSampleApplication.class)
public class RestTemplateTest {

    @Autowired
    RestRequestService restRequestService;

    @Test
    public void test() {
        RestTemplate restTemplate = new RestTemplate();
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        System.out.println(quote);
    }

    @Test
    public void testQuote() {
        // Jackson2ObjectMapperBuilder.json().build().addHandler()
        System.out.println(restRequestService.queryQuote());
    }
}
