package com.example.zookeeperLearn.origin;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * A simple example program to use DataMonitor to start and stop executables based on a zNode. The
 * program watches the specified zNode and saves the data that corresponds to the zNode in the
 * filesystem. It also starts the specified program with the specified arguments when the zNode
 * exists and kills the program if the zNode goes away.
 */
public class Executor implements Watcher, Runnable, DataMonitor.DataMonitorListener {

    private String zNode;

    private DataMonitor dataMonitor;

    private ZooKeeper zk;

    private String filename;

    private String[] exec;

    private Process child;

    public Executor(String hostPort, String zNode, String filename, String[] exec) throws KeeperException, IOException {
        this.filename = filename;
        this.exec = exec;
        zk = new ZooKeeper(hostPort, 3000, this);
        dataMonitor = new DataMonitor(zk, zNode, null, this);
    }

    @Override
    public void run() {
        try {
            synchronized (this) {
                while (!dataMonitor.dead) {
                    System.out.println("wait");
                    wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Watcher
    @Override
    public void process(WatchedEvent event) {
        System.out.println("receive event" + event);
        dataMonitor.process(event);
    }

    // DataMonitorListener
    @Override
    public void closing(int rc) {
        synchronized (this) {
            notifyAll();
        }
    }

    // DataMonitorListener
    @Override
    public void exists(byte[] data) {
        if (data == null) {
            if (child != null) {
                System.out.println("Killing process");
                child.destroy();
                try {
                    child.waitFor();
                } catch (InterruptedException e) {
                }
            }
            child = null;
        } else {
            if (child != null) {
                System.out.println("Stopping child");
                child.destroy();
                try {
                    child.waitFor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                FileOutputStream fos = new FileOutputStream(filename);
                fos.write(data);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("Starting child");
                child = Runtime.getRuntime().exec(exec);
                new StreamWriter(child.getInputStream(), System.out);
                new StreamWriter(child.getErrorStream(), System.err);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class StreamWriter extends Thread {

        private OutputStream os;

        private InputStream is;

        StreamWriter(InputStream is, OutputStream os) {
            this.is = is;
            this.os = os;
            start();
        }

        @Override
        public void run() {
            byte b[] = new byte[80];
            int rc;
            try {
                while ((rc = is.read(b)) > 0) {
                    os.write(b, 0, rc);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}