package com.example.rpcLearn.rpc2.test;

import com.example.rpcLearn.rpc2.ProxyClient;

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

            PersonReq personReq = new PersonReq();
            personReq.setName("nana");
            Result result2 = singleService.getResult(personReq);
            System.out.println(result2.getName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
