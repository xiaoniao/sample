package com.example.dubbo.learngateway.integration.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.dubbo.learngateway.integration.UserIntegration;
import com.example.dubbo.learntouristfacade.UserFacade;
import org.springframework.stereotype.Service;

/**
 *
 * Created by liuzz on 2018/04/27
 */
@Service("mergeableUserIntegration")
public class MergeableUserIntegrationImpl implements UserIntegration {

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            cluster = "mergeable")
    private UserFacade userFacade;

    @Override
    public String getUserName(String userId, String tag) {
        return userFacade.getUserName(userId, tag);
    }
}
