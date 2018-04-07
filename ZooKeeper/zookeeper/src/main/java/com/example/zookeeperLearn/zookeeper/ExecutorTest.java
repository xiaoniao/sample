package com.example.zookeeperLearn.zookeeper;

public class ExecutorTest {

    public static void main(String[] args) {
        String hostPort = "127.0.0.1:2194";
        String zNode = "/zk_test";
        String filename = "/Users/xiezx/file/test";
        String exec[] = new String[] {"/Applications/zookeeper-3.4.11/bin/zkCli.sh", "-server", "127.0.0.1:2194", "ls", "/"};


        try {
            new Executor(hostPort, zNode, filename, exec).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
