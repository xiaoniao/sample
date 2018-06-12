package com.example.springaopandaspectjlearn.cglib.sample1.bean;

/**
 * Created by liuzz on 2018/06/12
 */
public class PrivateTargetObject {

    private String name;

    private PrivateTargetObject () {

    }

    public PrivateTargetObject(String name) {
        this.name = name;
    }
}
