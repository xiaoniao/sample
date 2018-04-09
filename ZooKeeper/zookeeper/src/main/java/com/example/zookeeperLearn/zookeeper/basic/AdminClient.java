package com.example.zookeeperLearn.zookeeper.basic;

import java.io.IOException;
import java.util.List;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liuzz on 2018/04/09
 */
public class AdminClient implements Watcher {

    private static final String PREFIX = "________________";
    private Logger log = LoggerFactory.getLogger(Worker.class);

    private ZooKeeper zooKeeper;
    private String connectHost;
    private Integer sessionTimeout;

    public AdminClient(String connectHost, Integer sessionTimeout) {
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


    public void listState() {

        try {
            Stat stat = new Stat();
            byte[] bytes = zooKeeper.getData("/master", false, stat);
            log.info("{}, {}", PREFIX, new String(bytes));
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            List<String> list = zooKeeper.getChildren("/workers", false);
            for (String s : list) {
                log.info("{} worker : {}", PREFIX, s);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            List<String> list = zooKeeper.getChildren("/tasks", false);
            for (String s : list) {
                log.info("{} task : {}", PREFIX, s);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
