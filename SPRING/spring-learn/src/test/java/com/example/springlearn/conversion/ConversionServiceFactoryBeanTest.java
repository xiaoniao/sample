package com.example.springlearn.conversion;

import com.example.springlearn.SpringLearnApplication;
import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 实现FactoryBean ， 启动bean
 *
 * TypeConverterSupport
 *
 * SimpleTypeConverter 实际是用这个？？？？？
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringLearnApplication.class)
public class ConversionServiceFactoryBeanTest {

    @Value("${goods}")
    private String goods;

    @Value(("${goods}"))
    private UUID uuid;

//    @Value("${goods}")
//    private Goods goods2;

    @Test
    public void showProperties() {
        System.out.println("goods string : " + goods);
        System.out.println("goods uuid : " + uuid);
    }

}
