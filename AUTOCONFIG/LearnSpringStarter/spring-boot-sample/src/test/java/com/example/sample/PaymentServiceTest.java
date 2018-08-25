package com.example.sample;

import com.example.payment.service.PayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liuzhuang on 2018/8/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringSampleApplication.class)
public class PaymentServiceTest {

    @Autowired
    private PayService payService;

    @Test
    public void testPay() {
        payService.pay();
    }
}
