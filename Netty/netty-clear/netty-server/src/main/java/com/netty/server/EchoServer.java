package com.netty.server;

import com.netty.server.handler.ChannelInHandler1;
import com.netty.server.handler.ChannelInHandler2;
import com.netty.server.handler.ChannelOutHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * Created by liuzhuang on 7/2/18.
 */
public class EchoServer {
    private Logger log = LoggerFactory.getLogger(EchoServer.class);

    private int port;

    public EchoServer(int port) {
        this.port = port;
    }

    private void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // 入站从上到下(channelIn类型)，出站从下到上(channelOut类型)
                            socketChannel.pipeline().addLast(new ChannelInHandler1());
                            socketChannel.pipeline().addLast(new EchoServerHandler());
                            socketChannel.pipeline().addLast(new ChannelInHandler2());
                            socketChannel.pipeline().addLast(new ChannelOutHandler());
                        }
                    });
            ChannelFuture f = b.bind().sync();
            log.info("A");

            f.channel().closeFuture().sync();
            log.info("B");
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        new EchoServer(8888).start();
    }

}
