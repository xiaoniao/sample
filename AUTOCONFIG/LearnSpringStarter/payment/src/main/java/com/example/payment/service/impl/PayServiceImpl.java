package com.example.payment.service.impl;

import com.example.payment.properties.PaymentExtraProperties;
import com.example.payment.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liuzz on 2018/04/03
 */
@Service
public class PayServiceImpl implements PayService {

    @Autowired(required = false)
    private PaymentExtraProperties paymentExtraProperties;

    @Override
    public void pay() {
        System.out.println("正在支付.....");
        if (paymentExtraProperties != null) {
            System.out.println("地址:" + paymentExtraProperties.getAddress() + ":" + paymentExtraProperties.getPort());
        } else {
            System.out.println("使用默认配置 地址:" + "http://alipay.com:8080");
        }
    }
}
