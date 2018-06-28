package io.netty.example.definitive.book;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.example.definitive.serialize.java.SubscribeReqProto;
import io.netty.example.definitive.serialize.java.SubscribeRespProto;
import io.netty.example.definitive.serialize.java.UserInfo;
import io.netty.handler.codec.memcache.binary.BinaryMemcacheObjectAggregator;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

public final class BookClient {

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

                            p.addLast(new ProtobufVarint32FrameDecoder());
                            p.addLast(new ProtobufDecoder(SubscribeRespProto.SubscribeResp.getDefaultInstance()));
                            p.addLast(new ProtobufVarint32LengthFieldPrepender());
                            p.addLast(new ProtobufEncoder());
                            p.addLast(new BookClientHandler());
                        }
                    });

            ChannelFuture f = b.connect("127.0.0.1", 8007).sync();

            f.channel().closeFuture().sync();

        } finally {
            group.shutdownGracefully();

        }
    }

    public static class BookClientHandler extends ChannelInboundHandlerAdapter {
        private Logger log = LoggerFactory.getLogger("LZZ-HttpClientHandler");

        private volatile int counter;

        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            log.info("#####channelActive");
            for (int i = 0; i < 1; i++) {
                SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
                builder.setSubReqID(1);
                builder.setUserName("jack");
                builder.setProductName("taoBao");
                builder.setAddress("hz");
                ctx.writeAndFlush(builder.build());
            }
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
            SubscribeRespProto.SubscribeResp subscribeResp = (SubscribeRespProto.SubscribeResp) msg;
            log.info("#####channelRead msg:{} counter:{}", subscribeResp.getDesc(), ++counter);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) {
            log.info("#####channelReadComplete");
            ctx.flush();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }
    }
}
