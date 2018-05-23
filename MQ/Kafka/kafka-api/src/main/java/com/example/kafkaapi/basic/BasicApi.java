package com.example.kafkaapi.basic;

import kafka.admin.AdminUtils;
import kafka.utils.ZkUtils;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Created by liuzz on 2018/05/22
 */
@Component
public class BasicApi {

    private static String zkUrl = "localhost:2181";

    private static int sessionTimeout = 5000;

    private static int connectionTimeout = 5000;

    private static boolean isZkSecurityEnabled = false;


    public static void main(String[] args) {
        createTopic("liuzz-article", 1, 1, null);
    }


    public static void createTopic(String topic, int partitions, int replica, Properties properties) {

        ZkUtils zkUtils = ZkUtils.apply(zkUrl, sessionTimeout, connectionTimeout, isZkSecurityEnabled);

        if (AdminUtils.topicExists(zkUtils, topic)) {
            System.out.println("主题已经存在");
            return;
        }

    }
}
