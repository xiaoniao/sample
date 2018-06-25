package io.netty.example.definitive.time;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.example.definitive.serialize.java.UserInfo;
import io.netty.example.definitive.time.messagepack.MsgPackDecoder;
import io.netty.example.definitive.time.messagepack.MsgPackEncoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public final class TimeClient {

    public static void main(String[] args) throws Exception {

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
             .channel(NioSocketChannel.class)
             .option(ChannelOption.TCP_NODELAY, true)
             .handler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                     ChannelPipeline p = ch.pipeline();
                     // p.addLast(new LoggingHandler(LogLevel.INFO));

                     // 支持粘包 行解码器
                     // p.addLast(new LineBasedFrameDecoder(1024));

                     // 分隔符解码器
                     // ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
                     // p.addLast(new DelimiterBasedFrameDecoder(1024, delimiter));

                     // 定长解码器
                     // p.addLast(new FixedLengthFrameDecoder(19));

                     // 序列化解析
                     p.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2));
                     p.addLast("msgPack decoder", new MsgPackDecoder());

                     p.addLast("frameEncoder", new LengthFieldPrepender(2));
                     p.addLast("msgPack encoder", new MsgPackEncoder());

                     // p.addLast(new StringDecoder());
                     p.addLast(new TimeClientHandler());
                 }
             });

            ChannelFuture f = b.connect("127.0.0.1", 8007).sync();

            f.channel().closeFuture().sync();

        } finally {
            group.shutdownGracefully();

        }
    }

    public static class TimeClientHandler extends ChannelInboundHandlerAdapter {
        private Logger log = LoggerFactory.getLogger("LZZ-HttpClientHandler");

        private volatile int counter;

        // private String message = "message from client" + System.getProperty("line.separator");
        // private String message = "message from client$_";
        // private String message = "message from client boring";
        private List<UserInfo> userInfos = new ArrayList<>();

        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            log.info("#####channelActive");

//            for (int i = 0; i < 1; i++) {
//                byte[] bytes = message.getBytes();
//                ByteBuf byteBuf = Unpooled.buffer(bytes.length);
//                byteBuf.writeBytes(bytes);
//                ctx.writeAndFlush(byteBuf);
//            }

            for (int i = 0; i < 1000; i++) {
                ctx.write(new UserInfo().buildUserName("message from client" + i).buildUserId(i));
            }
            ctx.flush();
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
            log.info("#####channelRead msg:{} counter:{}", msg, ++counter);

            // Utils.printBody((ByteBuf) msg);

            // ctx.write(msg);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) {
            log.info("#####channelReadComplete");
            ctx.flush();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            // Close the connection when an exception is raised.
            cause.printStackTrace();
            ctx.close();
        }
    }
}
