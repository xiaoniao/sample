package com.example.RestTemplatesSample.controller;

import com.example.RestTemplatesSample.integration.model.HttpResult;
import com.example.RestTemplatesSample.integration.model.Quote;
import com.example.RestTemplatesSample.integration.model.Result;
import com.example.RestTemplatesSample.integration.service.RestRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuzz on 2018/05/04
 */
@RestController
public class TestController {

    @Autowired
    private RestRequestService restRequestService;

    /**
     * 模拟单元测试
     */
    @RequestMapping(value = "/quote", method = RequestMethod.GET)
    public Result quote() {
        return restRequestService.queryQuote();
    }

    /**
     * 模拟第三方服务
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public HttpResult test() {
        if (System.currentTimeMillis() % 2 == 0) {
            System.out.println("错误");
            return HttpResult.wrapErrorResult("错误");
        } else {
            return HttpResult.wrapSuccessfulResult(new Quote());
        }
    }

}
