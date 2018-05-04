package com.example.hystrixSample.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * Created by liuzz on 2018/05/03
 */
public class CommandHelloWorld extends HystrixCommand<String> {

    private String name;

    public CommandHelloWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey(name));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        return name;
    }

    @Override
    protected String getFallback() {
        return "Hello World Fallback";
    }
}
