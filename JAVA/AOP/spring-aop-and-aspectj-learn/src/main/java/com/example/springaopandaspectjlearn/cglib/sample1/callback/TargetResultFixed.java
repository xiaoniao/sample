package com.example.springaopandaspectjlearn.cglib.sample1.callback;

import net.sf.cglib.proxy.FixedValue;

/**
 * Created by liuzz on 2018/06/11
 */
public class TargetResultFixed implements FixedValue {

    @Override
    public Object loadObject() throws Exception {
        System.out.println("FixedValue");
        return 999;
    }
}
