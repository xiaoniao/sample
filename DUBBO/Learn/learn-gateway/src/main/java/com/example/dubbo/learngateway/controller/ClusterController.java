package com.example.dubbo.learngateway.controller;

import com.example.dubbo.learngateway.integration.UserIntegration;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuzz on 2018/04/27
 */
@RestController
public class ClusterController {

    @Resource(name = "availableUserIntegration")
    private UserIntegration availableUserIntegration;

    @Resource(name = "broadcastUserIntegration")
    private UserIntegration broadcastUserIntegration;

    @Resource(name = "failbackUserIntegration")
    private UserIntegration failbackUserIntegration;

    @Resource(name = "failfastUserIntegration")
    private UserIntegration failfastUserIntegration;

    @Resource(name = "failOverUserIntegration")
    private UserIntegration failOverUserIntegration;

    @Resource(name = "failSafeUserIntegration")
    private UserIntegration failSafeUserIntegration;

    @Resource(name = "forkingUserIntegration")
    private UserIntegration forkingUserIntegration;

    @Resource(name = "mergeableUserIntegration")
    private UserIntegration mergeableUserIntegration;

    @RequestMapping("/available")
    public String available() {
        System.out.println("available");
        return availableUserIntegration.getUserName("1", "available");
    }

    @RequestMapping("/broadcast")
    public String broadcast() {
        System.out.println("broadcast");
        return broadcastUserIntegration.getUserName("1", "broadcast");
    }

    @RequestMapping("/failback")
    public String failback() {
        System.out.println("failback");
        return failbackUserIntegration.getUserName("1", "failback");
    }

    @RequestMapping("/failfast")
    public String failfast() {
        System.out.println("failfast");
        return failfastUserIntegration.getUserName("1", "failfast");
    }

    @RequestMapping("/failOver")
    public String failOver() {
        System.out.println("failOver");
        return failOverUserIntegration.getUserName("1", "failOver");
    }

    @RequestMapping("/failSafe")
    public String failSafe() {
        System.out.println("failSafe");
        return failSafeUserIntegration.getUserName("1", "failSafe");
    }

    @RequestMapping("/forking")
    public String forking() {
        System.out.println("forking");
        return forkingUserIntegration.getUserName("1", "forking");
    }

    @RequestMapping("/mergeable")
    public String mergeable() {
        System.out.println("mergeable");
        return mergeableUserIntegration.getUserName("1", "mergeable");
    }
}
