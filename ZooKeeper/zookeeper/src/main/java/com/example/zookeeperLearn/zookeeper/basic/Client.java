package com.example.zookeeperLearn.zookeeper.basic;

import java.io.IOException;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.KeeperException.NodeExistsException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liuzz on 2018/04/09
 */
public class Client implements Watcher {

    private static final String PREFIX = "________________";
    private Logger log = LoggerFactory.getLogger(Worker.class);

    private ZooKeeper zooKeeper;
    private String connectHost;
    private Integer sessionTimeout;

    public Client(String connectHost, Integer sessionTimeout) {
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

    public String queueCommand(String command) throws NodeExistsException {
        while (true) {
            try {
                String name = zooKeeper.create("/tasks/task-", command.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
                log.info("{} {}任务添加成功", PREFIX, name);
                return name;
            } catch (NodeExistsException e) {
                log.info("{} {}任务已经存在", PREFIX, ("/tasks/task-" + command));
                throw new KeeperException.NodeExistsException();
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
