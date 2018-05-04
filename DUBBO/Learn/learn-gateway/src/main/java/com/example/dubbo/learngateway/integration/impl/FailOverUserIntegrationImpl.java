package com.example.dubbo.learngateway.integration.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.dubbo.learngateway.integration.UserIntegration;
import com.example.dubbo.learntouristfacade.UserFacade;
import org.springframework.stereotype.Service;

/**
 * FailOver n. 失效备援（为系统备援能力的一种，当系统中其中一项设备失效而无法运作时，另一项设备即可自动接手原失效系统所执行的工作）
 *
 * Created by liuzz on 2018/04/27
 */
@Service("failOverUserIntegration")
public class FailOverUserIntegrationImpl implements UserIntegration {

    @Reference(version = "1.0.0", application = "${dubbo.application.id}", cluster = "failover", retries = 1, timeout = 5000)
    private UserFacade userFacade;

    @Override
    public String getUserName(String userId, String tag) {
        return userFacade.getUserName(userId, tag);
    }
}
