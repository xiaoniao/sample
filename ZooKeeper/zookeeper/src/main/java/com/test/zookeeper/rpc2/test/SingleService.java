package com.test.zookeeper.rpc2.test;

/**
 * Created by liuzz on 2018/03/06
 */
public interface SingleService {

    String hello();

    String hello(String name);

    Result getResult(String name);

    Result getResult(PersonReq personReq);
}
