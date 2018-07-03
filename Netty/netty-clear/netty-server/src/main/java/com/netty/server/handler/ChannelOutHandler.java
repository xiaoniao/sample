package com.netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by liuzhuang on 7/2/18.
 */
public class ChannelOutHandler extends ChannelOutboundHandlerAdapter {
    private Logger log = LoggerFactory.getLogger(ChannelOutHandler.class);

    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
        log.info("read");
        super.read(ctx);
    }

    /**
     * TODO ? ProtobufEncoder 的 write方法为什么会执行？
     */
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        log.info("write");
        super.write(ctx, msg, promise);
    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        log.info("flush");
        super.flush(ctx);
    }
}
