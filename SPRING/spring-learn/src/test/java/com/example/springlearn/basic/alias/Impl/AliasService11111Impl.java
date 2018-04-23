package com.example.springlearn.basic.alias.Impl;

import com.example.springlearn.basic.alias.AliasService;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

/**
 * Created by liuzz on 2018/04/23
 */
@Service("aliasService11111")
public class AliasService11111Impl implements AliasService {

//    @AliasFor(value = "", attribute = "")

    @Override
    public void showName() {
        System.out.println("Alias11111 ......");
    }
}
