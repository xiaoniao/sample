package com.example.springaop;

import com.example.springaop.utils.HttpUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by liuzz on 2018/06/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringAopApplication.class)
public class TestHttpUtils {
    private Logger log = LoggerFactory.getLogger(TestHttpUtils.class);

    @Autowired
    private HttpUtils httpUtils;

    @Test
    public void testHttpUtils() {
        log.info(httpUtils.get());
    }
}
