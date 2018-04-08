package com.example.springannotaionlearn.importAndimportSource.importConfigure;

import com.example.springannotaionlearn.importAndimportSource.model.CompactDisc;
import com.example.springannotaionlearn.importAndimportSource.model.SgtPeppers;
import org.springframework.context.annotation.Bean;

/**
 * Created by liuzz on 2018/04/04
 */
public class CDConfig {

    @Bean
    public CompactDisc compactDisc() {
        System.out.println("new SgtPeppers ......");
        return new SgtPeppers();
    }
}
