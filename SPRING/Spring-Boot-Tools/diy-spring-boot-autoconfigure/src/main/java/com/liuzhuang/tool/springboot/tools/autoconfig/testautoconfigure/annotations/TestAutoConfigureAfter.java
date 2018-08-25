package com.liuzhuang.tool.springboot.tools.autoconfig.testautoconfigure.annotations;

import com.liuzhuang.tool.springboot.tools.autoconfig.testautoconfigure.model.JackAndRoseSon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liuzhuang on 2018/8/23.
 */
@Configuration
@AutoConfigureAfter(value = TestConditionalOnClass.class)
public class TestAutoConfigureAfter {
    private Logger log = LoggerFactory.getLogger(TestAutoConfigureAfter.class);

    @Bean
    public JackAndRoseSon jackAndRoseSon() {
        log.info("son is coming");
        return new JackAndRoseSon();
    }
}
