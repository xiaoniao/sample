/**
 * Alipay.com Inc. Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.test.zookeeper.rpc.rpc.registry;

import com.test.zookeeper.rpc.rpc.constant.Constant;
import com.test.zookeeper.rpc.util.LogUtils;
import java.util.concurrent.CountDownLatch;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;


/**
 * 简单RPC服务注册
 *
 * 注册方法是register(),该方法的主要功能如下：
 *      对目标服务器创建一个ZooKeeper实例
 *      如果可以成功创建ZooKeeper实例，则创建一个节点
 */
public class ServiceRegistry {

    // 日期记录器
    private static final Logger logger = LogManager.getLogger(ServiceRegistry.class);

    // 使用计数器实现同步
    private CountDownLatch latch = new CountDownLatch(1);

    private int timeout = Constant.DEFAULT_ZK_SESSION_TIMEOUT;

    private String registerPath = Constant.DEFAULT_ZK_REGISTRY_PATH;

    private String registerAddress;

    public void register(String data) {
        LogUtils.debug(logger, "注册服务{0}", data);
        if (data != null) {
            ZooKeeper zk = connectServer();
            if (zk != null) {
                // 创建节点
                createNode(zk, data);
            }
        }
    }

    /**
     *
     *创建zooKeeper
     * @return
     */
    private ZooKeeper connectServer() {
        ZooKeeper zk = null;
        try {
            LogUtils.info(logger, "创建zk,参数是：address:{0},timeout:{1}", registerAddress, timeout);
            // 创建一个zooKeeper实例，第一个参数是目标服务器地址和端口，第二个参数是session 超时时间，第三个参数是节点发生变化时的回调方法
            zk = new ZooKeeper(registerAddress, timeout, new Watcher() {

                public void process(WatchedEvent event) {
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                        // 计数器减一
                        latch.countDown();
                    }
                }
            });
            // 阻塞到计数器为0，直到节点的变化回调方法执行完成
            latch.await();

        } catch (Exception e) {
            LogUtils.error(logger, "connectServer exception", e);
        }
        // 返回ZooKeeper实例
        return zk;
    }

    /**
     *
     *
     * @param zk ZooKeeper的实例
     * @param data 注册数据
     */
    private void createNode(ZooKeeper zk, String data) {
        try {
            byte[] bytes = data.getBytes();
            /**
             * 创建一个节点，第一个参数是该节点的路径，第二个参数是该节点的初始化数据，第三个参数是该节点的ACL，第四个参数指定节点的创建策略
             */
            String createResult = zk.create(registerPath, bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL_SEQUENTIAL);
            LogUtils.info(logger, "创建的结果是：{0}", createResult);
        } catch (Exception e) {
            LogUtils.error(logger, "createNode exception", e);
        }
    }

    /**
     * Getter method for property <tt>timeout</tt>.
     *
     * @return property value of timeout
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * Setter method for property <tt>timeout</tt>.
     *
     * @param timeout value to be assigned to property timeout
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /**
     * Getter method for property <tt>registerPath</tt>.
     *
     * @return property value of registerPath
     */
    public String getRegisterPath() {
        return registerPath;
    }

    /**
     * Setter method for property <tt>registerPath</tt>.
     *
     * @param registerPath value to be assigned to property registerPath
     */
    public void setRegisterPath(String registerPath) {
        this.registerPath = registerPath;
    }

    /**
     * Getter method for property <tt>registerAddress</tt>.
     *
     * @return property value of registerAddress
     */
    public String getRegisterAddress() {
        return registerAddress;
    }

    /**
     * Setter method for property <tt>registerAddress</tt>.
     *
     * @param registerAddress value to be assigned to property registerAddress
     */
    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }

}
