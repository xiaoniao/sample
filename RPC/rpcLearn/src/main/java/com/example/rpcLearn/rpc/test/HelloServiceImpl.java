/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.example.rpcLearn.rpc.test;


import com.example.rpcLearn.rpc.rpc.RpcService;

/**
 *
 */
@RpcService(value = "helloService", inf = HelloService.class)
public class HelloServiceImpl implements HelloService {

    public String hello() {
        return "Hello! ";
    }
}
