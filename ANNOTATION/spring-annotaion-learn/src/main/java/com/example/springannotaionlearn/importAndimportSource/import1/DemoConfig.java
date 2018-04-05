package com.example.springannotaionlearn.importAndimportSource.import1;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by liuzz on 2018/04/04
 *
 * @Import 加载bean
 * @Import 需要搭配@Configuration使用
 *
 * 这个注解帮助我们将多个配置文件（可能是按功能分，或是按业务分）导入到单个主配置中，以避免将所有配置写在一个配置中。
 */
@Configuration
@Import(DemoService.class)
public class DemoConfig {

    public void print() {
        System.out.println("DemoConfig ........");
    }
}
