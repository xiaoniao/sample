package com.example.zookeeperLearn.zookeeper.basic;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.AsyncCallback.DataCallback;
import org.apache.zookeeper.AsyncCallback.StatCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liuzhuang on 2018/4/7.
 */
public class Master implements Watcher {


    private static final String PREFIX = "________________";
    private Logger log = LoggerFactory.getLogger(Master.class);

    private ZooKeeper zooKeeper;
    private String connectHost;
    private Integer sessionTimeout;

    private String masterNodeName = "/master";
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
     * zookeeper会自动和Zookeeper服务器保持连接，如果和一个会话断开连接，会自动连接到另一台Zookeeper服务器上。
     *
     * 三、和服务器连上和断开都会收到通知。
     */
    private ZooKeeper createZookeeper() throws IOException {
        return new ZooKeeper(connectHost, sessionTimeout, this);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        log.info("{} ZOOKEEPER EVENT : {}", PREFIX, watchedEvent);
        printSessionId();
    }

    private void printSessionId() {
        log.info("{} ZOOKEEPER SESSION_ID:{}", PREFIX, zooKeeper.getSessionId(), zooKeeper);
    }

    /**
     * 竞争成为Master节 - 谁先创建/master节点谁是Master
     *
     */
    public void runForMaster() {
        List<ACL> aclList = ZooDefs.Ids.OPEN_ACL_UNSAFE;
        CreateMode createMode = CreateMode.EPHEMERAL;
        zooKeeper.create(masterNodeName, serverId.getBytes(), aclList, createMode, masterCreateCallback, null);
    }

    private AsyncCallback.StringCallback masterCreateCallback = new AsyncCallback.StringCallback() {
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

            if (isLeader) {
                bootstrap();
            } else {
                watchMaster();
            }
        }
    };

    /**
     * 检查是否是master
     */
    public void checkMaster() {
        zooKeeper.getData(masterNodeName, false, masterCheckCallback, null);
    }

    private DataCallback masterCheckCallback = (rc, path, ctx, data, stat) -> {
        switch (KeeperException.Code.get(rc)) {
            case CONNECTIONLOSS:
                checkMaster();
                break;
            case NONODE:
                runForMaster();
                break;
        }
    };

    public void bootstrap() {
        createParent("/workers", new byte[0]);
        createParent("/assign", new byte[0]);
        createParent("/tasks", new byte[0]);
        createParent("/status", new byte[0]);
    }

    private void createParent(String path, byte[] data) {
        zooKeeper.create(path, data, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, (rc, p, ctx, name) -> {

            switch (Code.get(rc)) {
                case OK:
                    log.info("{} {}创建成功", PREFIX, path);
                    break;
                case NODEEXISTS:
                    log.info("{} {}节点已经存在", PREFIX, path);
                    break;
                case CONNECTIONLOSS:
                    createParent(path, data);
                    break;
                default:
                    log.info("{} {}", PREFIX, KeeperException.create(Code.get(rc), path));
                    break;
            }
        }, null);
    }

    private void watchMaster() {
        log.info("{}监听 /master 状态", PREFIX);
        zooKeeper.exists(masterNodeName, event -> {

            // 只会收到一次通知
            EventType eventType = event.getType();


            log.info("{}{}", PREFIX, eventType);
        }, (rc, path, ctx, stat) -> {
            log.info("{} ====== {}", PREFIX, rc);
        }, null);
    }

}
