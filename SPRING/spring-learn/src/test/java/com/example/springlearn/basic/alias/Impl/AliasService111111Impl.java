package com.example.springlearn.basic.alias.Impl;

import com.example.springlearn.basic.alias.AliasService;
import org.springframework.stereotype.Service;

/**
 * Created by liuzz on 2018/04/23
 */
@Service("aliasService11111")
public class AliasService111111Impl implements AliasService {

    @Override
    public void showName() {
        System.out.println("Alias11111 ......");
    }
}
