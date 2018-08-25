package com.liuzhuang.tool.springboot.tools.autoconfig.testautoconfigure.annotations;//package com.liuzhuang.tool.springboot.tools.sample.testautoconfigure.annotations;

import com.liuzhuang.tool.springboot.tools.autoconfig.testautoconfigure.model.MyConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liuzhuang on 2018/8/23.
 */
@Configuration()
public class TestConfiguration {

    @Bean
    public MyConfiguration myConfiguration() {
        System.out.println("myConfiguration");
        return new MyConfiguration();
    }
}
