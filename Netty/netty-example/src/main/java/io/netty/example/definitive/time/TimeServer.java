package io.netty.example.definitive.time;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.example.definitive.serialize.java.UserInfo;
import io.netty.example.definitive.time.serialize.MsgPackDecoder;
import io.netty.example.definitive.time.serialize.MsgPackEncoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

public final class TimeServer {

    public static void main(String[] args) throws Exception {

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            // p.addLast(new LoggingHandler(LogLevel.INFO));

                            // 解决粘包问题 以\n \r\n结束  行解码器
                            // p.addLast(new LineBasedFrameDecoder(1024));

                            // 分隔符解码器
                            // ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
                            // p.addLast(new DelimiterBasedFrameDecoder(1024, delimiter));

                            // 定长解码器
                            // p.addLast(new FixedLengthFrameDecoder(26));

                            // 消息头报文长度解码器
                            p.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2));
                            // 序列化解析
                            p.addLast("msgPack decoder", new MsgPackDecoder());

                            p.addLast("frameEncoder", new LengthFieldPrepender(2));
                            p.addLast("msgPack encoder", new MsgPackEncoder());

                            // 将接受到的对象转换成字符串
                            // p.addLast(new StringDecoder());
                            p.addLast(new TimeServerHandler());
                        }
                    });

            ChannelFuture f = b.bind(8007).sync();

            f.channel().closeFuture().sync();

        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private static class TimeServerHandler extends ChannelInboundHandlerAdapter {
        private Logger log = LoggerFactory.getLogger("LZZ-TimeServerHandler");

        private volatile int counter;

        // private String message = "message from server" + System.getProperty("line.separator");
        // private String message = "message from server$_";
        // private String message = "message from server";

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
            log.info("#####channelRead msg:{} counter:{}", msg, ++counter);

            // Utils.printBody((ByteBuf) msg);

            // ctx.write(Unpooled.copiedBuffer(message.getBytes()));

            UserInfo userInfo = new UserInfo().buildUserName("message from server").buildUserId(1);
            ctx.write(userInfo);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) {
            log.info("#####channelReadComplete");
            ctx.flush();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            log.info("#####exceptionCaught");
            cause.printStackTrace();
            ctx.close();
        }
    }
}
