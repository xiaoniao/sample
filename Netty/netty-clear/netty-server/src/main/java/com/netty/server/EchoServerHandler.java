package com.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liuzhuang on 7/2/18.
 *
 * 服务端入站事件（接收数据）
 */
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    private Logger log = LoggerFactory.getLogger(EchoServerHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        log.info("接收到的数据：{}", byteBuf.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("回传数据");
        ChannelFuture channelFuture = ctx.writeAndFlush(Unpooled.copiedBuffer("服务器收到消息", CharsetUtil.UTF_8));
        // ChannelFuture channelFuture = ctx.channel().writeAndFlush(Unpooled.copiedBuffer("服务器收到消息", CharsetUtil.UTF_8)); // 直接使用channel 会从出站handler开始流动
        channelFuture.addListener(ChannelFutureListener.CLOSE); // 关闭后客户端也随之关闭
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
