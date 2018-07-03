package com.netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liuzhuang on 7/2/18.
 */
public class ChannelInHandler1 extends ChannelInboundHandlerAdapter {
    private Logger log = LoggerFactory.getLogger(ChannelInHandler1.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("channelRead");
        super.channelRead(ctx, msg); // ctx.fireChannelRead(msg);
    }
}
