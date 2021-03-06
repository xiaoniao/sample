package com.example.springlearn.basic.alias;

import com.example.springlearn.SpringLearnApplication;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liuzz on 2018/04/23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringLearnApplication.class)
public class AliasTest {

    @Autowired()
    @Qualifier("aliasService11111")
    private AliasService aliasService1;

    @Resource(name = "aliasService22222")
    private AliasService aliasService2;

    @Test
    public void test() {
        aliasService1.showName();
        aliasService2.showName();
    }
}