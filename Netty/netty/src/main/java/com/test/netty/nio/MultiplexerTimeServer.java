package com.test.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO 服务端
 *
 * Created by liuzhuang on 2018/3/1.
 */
public class MultiplexerTimeServer implements Runnable {

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    private volatile boolean stop;


    public MultiplexerTimeServer(int port) {
        try {
            // Opens a selector
            selector = Selector.open();

            // Opens a server-socket channel.
            // The new channel's socket is initially unbound;
            // it must be bound to a specific address via one of its socket's {@link java.net.ServerSocket#bind(SocketAddress) bind}
            // methods before connections can be accepted.
            serverSocketChannel = ServerSocketChannel.open();

            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);

            // Registers this channel with the given selector, returning a selection key.
            // The interest set for the resulting key
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("The time server is start in port : " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                // Selects a set of keys whose corresponding channels are ready for I/O operations.
                selector.select(1000);

                // Returns this selector's selected-key set.
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();

                    try {
                        handleInput(selectionKey);
                    } catch (Exception e) {
                        e.printStackTrace();

                        if (selectionKey != null) {
                            selectionKey.cancel();
                            if (selectionKey.channel() != null) {
                                selectionKey.channel().close();
                            }
                        }
                    }
                }

            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        // stop
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isValid()) {

            if (selectionKey.isAcceptable()) {
                System.out.println("isAcceptable");
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                SocketChannel socketChannel = serverSocketChannel.accept();
                System.out.println("isBlocking : " + socketChannel.isBlocking() + " - " + socketChannel);
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
            }

            if (selectionKey.isReadable()) {
                System.out.println("isReadable");
                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int readBytes = socketChannel.read(byteBuffer);
                if (readBytes > 0) {
                    byteBuffer.flip();

                    byte[] bytes = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bytes);

                    String body = new String(bytes, "UTF-8");
                    System.out.println("The time server receive order : " + body);

                    String responseTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date().toString() : "BAD ORDER";
                    doWrite(socketChannel, responseTime);

                } else if ((readBytes < 0)) {
                    serverSocketChannel.close();
                    socketChannel.close();
                }
            }
        }
    }

    private void doWrite(SocketChannel socketChannel, String response) throws IOException {
        if (response == null || response.trim().length() <= 0) {
            return;
        }

        byte[] bytes = response.getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        socketChannel.write(writeBuffer);
    }

    public void stop() {
        stop = true;
    }
}
