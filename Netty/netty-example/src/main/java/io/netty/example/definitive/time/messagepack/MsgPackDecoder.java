package io.netty.example.definitive.time.messagepack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by liuzhuang on 6/24/18.
 */
public class MsgPackDecoder extends MessageToMessageDecoder<ByteBuf> {
    private Logger log = LoggerFactory.getLogger("LZZ-MsgPackDecoder");

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        MessagePack messagePack = new MessagePack();
        int length = byteBuf.readableBytes();
        byte[] bytes = new byte[length];
        byteBuf.getBytes(byteBuf.readerIndex(), bytes, 0, length);
        list.add(messagePack.read(bytes));
    }
}
