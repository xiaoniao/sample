//package com.example.zookeeperLearn.zookeeper.basic;
//
//
//import java.io.IOException;
//import java.util.List;
//
//import org.apache.zookeeper.*;
//import org.apache.zookeeper.ZooDefs.Ids;
//import org.apache.zookeeper.data.ACL;
//import org.apache.zookeeper.data.Stat;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * Created by liuzz on 2018/02/27
// */
//public class Basic {
//
//    private static Logger log = LoggerFactory.getLogger(Basic.class);
//    private static final String CONNECT_HOST = "127.0.0.1:2181";
//    private static final Integer SESSION_TIMEOUT = 15000;
//
//
//    public static void main(String[] args) throws IOException, InterruptedException {
//
//        String nodeName = "/zk_nana";
//        String nodeData = "this is my love";
//
//        Master master = new Master(CONNECT_HOST, SESSION_TIMEOUT);
//        master.startZooKeeper();
//        master.runForMaster();
//
//
////        createNode(zooKeeper, nodeName, nodeData);
////
////        String data = getData(zooKeeper, nodeName);
////
////        log.info("data:{}", data);
////
////        // deleteNode(zooKeeper, nodeName);
////
////        getDataWithCallBack(zooKeeper, nodeName);
//
//        try {
//            Thread.sleep(50000000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void deleteNode(ZooKeeper zooKeeper, String nodeName) {
//        Integer version = getVersion(zooKeeper, nodeName);
//        if (version == null) {
//            log.info("node:{} not exist", nodeName);
//            return;
//        }
//        zooKeeper.delete(nodeName, version, (int rc, String path, Object ctx) -> {
//            log.info("rc:{}" + rc + " {}" + KeeperException.Code.get(rc) + "  path:{} " + path + "  ctx:{} " + ctx);
//        }, "delete");
//    }
//
//    private static String getData(ZooKeeper zooKeeper, String nodeName) {
//        try {
//            Stat stat = new Stat();
//            byte[] dataByte = zooKeeper.getData(nodeName, false, stat);
//            return new String(dataByte);
//        } catch (KeeperException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private static Integer getVersion(ZooKeeper zooKeeper, String nodeName) {
//        try {
//            Stat stat = new Stat();
//            zooKeeper.getData(nodeName, false, stat);
//            return stat.getAversion();
//        } catch (KeeperException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private static void createNode(ZooKeeper zooKeeper, String nodeName, String data) {
//        List<ACL> aclList = Ids.OPEN_ACL_UNSAFE;
//        CreateMode createMode = CreateMode.PERSISTENT;
//        zooKeeper.create(nodeName, data.getBytes(), aclList, createMode, new MyStringCallback(), "love");
//    }
//
//    public static class MyStringCallback implements AsyncCallback.StringCallback {
//
//        @Override
//        public void processResult(int rc, String path, Object ctx, String name) {
//            log.info("rc:{}" + rc + " {} " + KeeperException.Code.get(rc) + "  path:{} " + path + "  name:{} " + name + "  ctx:{} " + ctx);
//        }
//    }
//
//    private static void getDataWithCallBack(ZooKeeper zooKeeper, String p) {
//        zooKeeper.getData(p, (WatchedEvent event) -> {
//            log.info("receive event:{}", event);
//            getDataWithCallBack(zooKeeper, p);
//
//        }, (int rc, String path, Object ctx, byte[] data, Stat stat) -> {
//            log.info("receive data:{}", new String(data));
//
//        }, null);
//    }
//}
