package com.example.springaopandaspectjlearn.cglib.sample1;

import net.sf.cglib.proxy.FixedValue;

/**
 * Created by liuzz on 2018/06/11
 */
public class TargetResultFixed implements FixedValue {

    @Override
    public Object loadObject() throws Exception {
        System.out.println("锁定结果");
        Object obj = 999;
        return obj;
    }
}
