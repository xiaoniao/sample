package com.test.zookeeper.rpc2.test;

import com.test.zookeeper.rpc2.ProxyClient;

/**
 * Created by liuzz on 2018/03/07
 */
public class ClientSample {

    public static void main(String[] args) {
        try {
            SingleService singleService = ProxyClient.proxy(SingleService.class, "localhost", 12000);
            String result = singleService.hello();
            System.out.println(result);

            result = singleService.hello("jck");
            System.out.println(result);

            Result result1 = singleService.getResult("pic");
            System.out.println(result1.getName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
