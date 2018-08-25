package com.liuzhuang.tool.springboot.tools.autoconfig.testautoconfigure.annotations;

import com.liuzhuang.tool.springboot.tools.autoconfig.testautoconfigure.model.JackFather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liuzhuang on 2018/8/23.
 */
@Configuration
@AutoConfigureBefore(value = TestConditionalOnClass.class)
@AutoConfigureOrder(1)
public class TestAutoConfigureBefore {
    private Logger log = LoggerFactory.getLogger(TestAutoConfigureBefore.class);

    @Bean
    public JackFather jackFather() {
        log.info("jackFather is coming");
        return new JackFather();
    }
}
