package com.example.zookeeperLearn.origin;


import java.io.IOException;
import org.apache.curator.utils.ZookeeperFactory;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * Created by liuzz on 2018/02/27
 */
public class BasicUse {


    public static void main(String[] args) throws IOException {

        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2194", 1500, (WatchedEvent event) -> {
            System.out.println(event);
            System.out.println("");
        });


        zooKeeper.create("/zk_nana", "nana".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, (int rc, String path, Object ctx, String name) -> {
            System.out.println("rc:" + rc + " " + KeeperException.Code.get(rc) + "  path: " + path + "  name: " + name + "  ctx: " + ctx);
            System.out.println("");
        }, "love");


//        int version = 0;
//        try {
//            Stat stat = new Stat();
//            byte[] dataByte = zooKeeper.getData("/zk_nana0000000003", false, stat);
//            String data = new String(dataByte);
//            System.out.println(data);
//            version = stat.getAversion();
//        } catch (KeeperException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        zooKeeper.delete("/zk_nana0000000003", version, (int rc, String path, Object ctx) -> {
//            System.out.println("rc:" + rc + " " + KeeperException.Code.get(rc) + "  path: " + path + "  ctx: " + ctx);
//            System.out.println();
//        }, "delete");


        getData(zooKeeper, "/zk_nana");

        System.out.println("sessionId: " + zooKeeper.getSessionId() + " sessionPassword: " + zooKeeper.getSessionPasswd());

        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void getData(ZooKeeper zooKeeper, String p) {
        zooKeeper.getData(p, (WatchedEvent event) -> {

            System.out.println("-------Watcher-------");
            System.out.println(event);
            System.out.println("");

            getData(zooKeeper, p);

        },  (int rc, String path, Object ctx, byte data[], Stat stat) -> {

            System.out.println("-------DataCallback-------");
            System.out.println("rc:" + rc + " " + KeeperException.Code.get(rc) + "  path: " + path + "  ctx: " + ctx + "  data: " + new String(data));
            printStat(stat);
            System.out.println("");

        }, null);
    }

    private static void printStat(Stat stat) {
        System.out.println("czxid: " + stat.getCzxid() + " mzxid: " + stat.getMzxid()
                + " ctime: " + stat.getCtime() + " mtime: " + stat.getMtime()
                + " version: " + stat.getVersion() + " cversion: " + stat.getCversion()
                + " aversion: " + stat.getAversion() + " ephemeralOwner: " + stat.getEphemeralOwner()
                + " dataLength: " + stat.getDataLength() + " numChildren: " + stat.getNumChildren()
                + " pzxid: " + stat.getPzxid());
    }
}
