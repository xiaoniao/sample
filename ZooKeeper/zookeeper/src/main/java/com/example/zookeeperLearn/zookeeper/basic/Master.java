package com.example.zookeeperLearn.zookeeper.basic;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * Created by liuzhuang on 2018/4/7.
 */
public class Master implements Watcher {

    private Logger log = LoggerFactory.getLogger(Master.class);
    private ZooKeeper zooKeeper;
    private String connectHost;
    private Integer sessionTimeout;
    private String nodeName = "/master";
    private String serverId = Long.toString(new Random().nextLong());
    private boolean isLeader;

    public Master(String connectHost, Integer sessionTimeout) {
        this.connectHost = connectHost;
        this.sessionTimeout = sessionTimeout;
    }

    public void startZooKeeper() throws IOException {
        zooKeeper = createZookeeper();
        printSessionId(); // 此时还没有连接上Zookeeper
    }

    public void stopZooKeeper() throws InterruptedException {
        zooKeeper.close();
    }

    /**
     * 一、zookeeper实例代表于Zookeeper之间的一个会话。
     *
     * 二、连接到Zookeeper后，会有一个后台守护线程来维护这个Zookeeper会话。
     *      zookeeper会自动和Zookeeper服务器保持连接，如果和一个会话断开连接，会自动连接到另一台Zookeeper服务器上。
     *
     * 三、和服务器连上和断开都会收到通知。
     *
     */
    private ZooKeeper createZookeeper() throws IOException {
        return new ZooKeeper(connectHost, sessionTimeout, this);
    }

    private void printSessionId() {
        log.info("###### ZOOKEEPER SESSION_ID:{}", zooKeeper.getSessionId(), zooKeeper);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        log.info("###### ZOOKEEPER EVENT : {}", watchedEvent);
        printSessionId();
    }

    private AsyncCallback.StringCallback stringCallback = new AsyncCallback.StringCallback() {
        @Override
        public void processResult(int rc, String path, Object ctx, String name) {

            switch (KeeperException.Code.get(rc)) {
                case CONNECTIONLOSS:
                    checkMaster();
                    break;
                case OK:
                    isLeader = true;
                    break;
                default:
                    isLeader = false;
            }
            log.info("是否是Master:{}", isLeader);
        }
    };

    /**
     * 竞争成为Master节点，谁先创建#nodeName节点谁是Master
     */
    public void runForMaster() throws InterruptedException {
        List<ACL> aclList = ZooDefs.Ids.OPEN_ACL_UNSAFE;
        CreateMode createMode = CreateMode.EPHEMERAL;
        zooKeeper.create(nodeName, serverId.getBytes(), aclList, createMode, stringCallback, null);
    }

    public boolean checkMaster() {
        while (true) {
            try {
                Stat stat = new Stat();
                byte[] dataByte = zooKeeper.getData(nodeName, false, stat);
                String data = new String(dataByte);
                isLeader = data.equals(serverId);
                return true;
            } catch (KeeperException.NodeExistsException e) {
                return false;

            } catch (KeeperException e) {
                e.printStackTrace();

            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }
    }

}
