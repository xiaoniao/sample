package com.example.springlearn.basic.alias.Impl;

import com.example.springlearn.basic.alias.AliasService;
import org.springframework.stereotype.Service;

/**
 * Created by liuzz on 2018/04/23
 */
@Service("aliasService22222")
public class AliasService22222Impl implements AliasService {

    @Override
    public void showName() {
        System.out.println("Alias22222 ......");
    }
}