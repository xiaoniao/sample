package io.netty.example.definitive.time.serialize;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liuzhuang on 6/24/18.
 */
public class MsgPackEncoder extends MessageToByteEncoder<Object> {
    private Logger log = LoggerFactory.getLogger("LZZ-MsgPackEncoder");

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        MessagePack messagePack = new MessagePack();
        try {
            byte[] bytes = messagePack.write(o);
            byteBuf.writeBytes(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
