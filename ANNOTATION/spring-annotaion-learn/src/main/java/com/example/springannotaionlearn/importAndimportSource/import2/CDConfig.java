package com.example.springannotaionlearn.importAndimportSource.import2;

import com.example.springannotaionlearn.importAndimportSource.bean.CompactDisc;
import com.example.springannotaionlearn.importAndimportSource.bean.SgtPeppers;
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
