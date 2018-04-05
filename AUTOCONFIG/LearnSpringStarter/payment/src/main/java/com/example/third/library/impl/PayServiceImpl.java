package com.example.third.library.impl;

import com.example.third.library.PayService;
import org.springframework.stereotype.Service;

/**
 * Created by liuzz on 2018/04/03
 */
@Service
public class PayServiceImpl implements PayService {

    @Override
    public void pay() {
        System.out.println("正在支付.....");
    }
}
