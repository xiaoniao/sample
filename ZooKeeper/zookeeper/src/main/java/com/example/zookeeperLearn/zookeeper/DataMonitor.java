package com.example.zookeeperLearn.zookeeper;


import java.util.Arrays;
import org.apache.zookeeper.AsyncCallback.StatCallback;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;


/**
 * A simple class that monitors the data and existence of a ZooKeeper node.
 * It uses asynchronous ZooKeeper APIs.
 */
public class DataMonitor implements Watcher, StatCallback {

    private ZooKeeper zk;

    private String zNode;

    private Watcher chainedWatcher;

    boolean dead;

    private DataMonitorListener listener;

    private byte prevData[];

    public DataMonitor(ZooKeeper zk, String zNode, Watcher chainedWatcher, DataMonitorListener listener) {
        this.zk = zk;
        this.zNode = zNode;
        this.chainedWatcher = chainedWatcher;
        this.listener = listener;
        zk.exists(zNode, true, this, null);
    }

    public interface DataMonitorListener {

        void exists(byte data[]);

        void closing(int rc);
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("process event");
        String path = event.getPath();
        if (event.getType() == Event.EventType.None) {
            // We are are being told that the state of the connection has changed
            switch (event.getState()) {
                case SyncConnected:
                    // In this particular example we don't need to do anything
                    // here - watches are automatically re-registered with
                    // server and any watches triggered while the client was
                    // disconnected will be delivered (in order of course)
                    break;
                case Expired:
                    // It's all over
                    dead = true;
                    listener.closing(KeeperException.Code.SessionExpired);
                    break;
            }
        } else {
            if (path != null && path.equals(zNode)) {
                // Something has changed on the node, let's find out
                zk.exists(zNode, true, this, null);
            }
        }
        if (chainedWatcher != null) {
            chainedWatcher.process(event);
        }
    }

    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        System.out.println("processResult:" + rc + " - " + path + " - " + ctx + " - " + stat);
        boolean exists;
        switch (rc) {
            case Code.Ok:
                exists = true;
                break;
            case Code.NoNode:
                exists = false;
                break;
            case Code.SessionExpired:
            case Code.NoAuth:
                dead = true;
                listener.closing(rc);
                return;
            default:
                // Retry errors
                zk.exists(zNode, true, this, null);
                return;
        }

        byte b[] = null;
        if (exists) {
            try {
                b = zk.getData(zNode, false, null);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                return;
            }
        }

        if ((b == null && b != prevData) || (b != null && !Arrays.equals(prevData, b))) {
            listener.exists(b);
            prevData = b;
        }
    }
}