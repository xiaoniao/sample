package com.example.springannotaionlearn.importAndimportSource.importConfigure;

import com.example.springannotaionlearn.importAndimportSource.model.CDPlayer;
import com.example.springannotaionlearn.importAndimportSource.model.CompactDisc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by liuzz on 2018/04/04
 */
@Configuration
@Import(CDConfig.class) // 加载bean
@ImportResource("cons-injec.xml") // 从xml加载bean
public class CDPlayerConfig {

    @Bean
    public CDPlayer cdPlayer(CompactDisc compactDisc) {
        return new CDPlayer(compactDisc);
    }
}
