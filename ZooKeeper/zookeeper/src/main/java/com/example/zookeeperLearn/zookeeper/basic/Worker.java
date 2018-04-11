package com.example.zookeeperLearn.zookeeper.basic;

import java.io.IOException;
import java.util.Random;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 任务队列化
 *
 * Created by liuzz on 2018/04/09
 */
public class Worker implements Watcher {


    private static final String PREFIX = "________________";
    private Logger log = LoggerFactory.getLogger(Client.class);

    private ZooKeeper zooKeeper;
    private String connectHost;
    private Integer sessionTimeout;

    private String serverId = Long.toString(new Random().nextLong());
    private String status;

    public Worker(String connectHost, Integer sessionTimeout) {
        this.connectHost = connectHost;
        this.sessionTimeout = sessionTimeout;
    }

    public void startZooKeeper() throws IOException {
        zooKeeper = new ZooKeeper(connectHost, sessionTimeout, this);
    }

    @Override
    public void process(WatchedEvent event) {
        log.info("{} {}", PREFIX, event);
    }

    public void register() {
        zooKeeper.create("/workers/worker-" + serverId, "Idle".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, (rc, p, ctx, name) -> {

            switch (Code.get(rc)) {
                case OK:
                    log.info("{} {}创建成功", PREFIX, ("/workers/worker-" + serverId));
                    break;
                case NODEEXISTS:
                    log.info("{} {}节点已经存在", PREFIX, ("/workers/worker-" + serverId));
                    break;
                case CONNECTIONLOSS:
                    register();
                    break;
                default:
                    log.info("{} {}", PREFIX, KeeperException
                            .create(Code.get(rc), ("/workers/worker-" + serverId)));
                    break;
            }
        }, null);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private synchronized void updateStatus(String status) {
        if (status.equals(this.status)) {
            zooKeeper.setData("/workers/worker-" + serverId, status.getBytes(), -1,  (rc, p, ctx, name) -> {
                switch (Code.get(rc)) {
                    case CONNECTIONLOSS:
                        updateStatus(status);
                        break;
                }
            }, status);
        }
    }
}
