package com.example.dubbo.learntourist.facade.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.dubbo.learntouristfacade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.AbstractEnvironment;

/**
 * Created by liuzz on 2018/04/27
 */
@Service(version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class UserFacadeImpl implements UserFacade {

    @Value("${machine-number}")
    private String number;

    @Autowired
    private AbstractEnvironment abstractEnvironment;

    @Override
    public String getUserName(String userId, String tag) {
        if (number.equals("2")) {
            System.out.println("sleep 10 秒");
            try {
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(System.currentTimeMillis() + " : " + number + " - " + tag);
        return "hello dubbo users";
    }
}
