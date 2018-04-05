package com.example.rpcLearn.rpc2.test;

import java.io.Serializable;

/**
 * Created by liuzz on 2018/03/07
 */
public class Result implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
