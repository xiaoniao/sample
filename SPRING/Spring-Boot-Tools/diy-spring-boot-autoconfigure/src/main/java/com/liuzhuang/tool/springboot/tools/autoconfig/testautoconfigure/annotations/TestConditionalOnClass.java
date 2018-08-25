package com.liuzhuang.tool.springboot.tools.autoconfig.testautoconfigure.annotations;

import com.liuzhuang.tool.springboot.tools.autoconfig.testautoconfigure.model.Rose;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liuzhuang on 2018/8/23.
 */
@Configuration
@ConditionalOnClass(name = "com.liuzhuang.tool.thirdparty.model.Jack")
public class TestConditionalOnClass {
    private Logger log = LoggerFactory.getLogger(TestConditionalOnClass.class);

    @Bean
    public Rose rose() {
        log.info("rose is coming");
        return new Rose();
    }
}
