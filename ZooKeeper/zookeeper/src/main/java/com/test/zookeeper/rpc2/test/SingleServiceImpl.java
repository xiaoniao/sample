package com.test.zookeeper.rpc2.test;

import com.test.zookeeper.rpc2.anno.SingleRpc;

/**
 * Created by liuzz on 2018/03/06
 */
@SingleRpc(interf = SingleService.class)
public class SingleServiceImpl implements SingleService {

    @Override
    public String hello() {
        System.out.println("hello");
        return "hello";
    }

    @Override
    public String hello(String name) {
        return name;
    }

    @Override
    public Result getResult(String name) {
        Result result = new Result();
        result.setName(name);
        return result;
    }

    @Override
    public Result getResult(PersonReq personReq) {
        Result result = new Result();
        result.setName(personReq.getName());
        return result;
    }
}
