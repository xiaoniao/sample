package com.example.zookeeperLearn.zookeeper.basic;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.zookeeper.KeeperException.NodeExistsException;
import org.apache.zookeeper.Watcher;

/**
 * Created by liuzz on 2018/04/09
 */
public class Demo {

    public static void main(String[] args) throws IOException, InterruptedException, NodeExistsException {
        Master master = new Master("127.0.0.1:2194", 15000);
        master.startZooKeeper();
        master.runForMaster();

        sleep(2000);
        TimeUnit.SECONDS.sleep(2);
        master.startZooKeeper();
        sleep(2000);

        Worker worker = new Worker("127.0.0.1:2194", 15000);
        worker.startZooKeeper();
        worker.register();
        sleep(1000);
        worker.setStatus("hello");

        Client client = new Client("127.0.0.1:2194", 15000);
        client.startZooKeeper();
        client.queueCommand("ls");

        AdminClient adminClient = new AdminClient("127.0.0.1:2194", 15000);
        adminClient.startZooKeeper();
        adminClient.listState();
        sleep(200000);
    }

    private static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
