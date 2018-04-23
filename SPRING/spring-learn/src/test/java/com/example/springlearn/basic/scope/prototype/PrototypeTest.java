package com.example.springlearn.basic.scope.prototype;

import com.example.springlearn.SpringLearnApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * scope n. 范围；余地；视野；眼界；导弹射程
 * Prototype n. 原型；标准，模范
 *
 * Proxy n. 代理人；委托书；代用品
 * ScopedProxyMode 代理方式 INTERFACES TARGET_CLASS
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringLearnApplication.class)
public class PrototypeTest {

    @Autowired
    private SingletonService singletonService;

    @Test
    public void showInstance() {
        singletonService.displayInstance();
        singletonService.displayInstance();
        singletonService.displayInstance();
    }
}
