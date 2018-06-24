package io.netty.example.definitive.time;

import io.netty.buffer.ByteBuf;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * Created by liuzhuang on 6/24/18.
 */
public class Utils {

    private static Logger log = LoggerFactory.getLogger("LZZ-UTILS");

    public static void printBody(ByteBuf msg) throws UnsupportedEncodingException {
        byte[] req = new byte[msg.readableBytes()];
        msg.readBytes(req);
        String body = new String(req, CharsetUtil.UTF_8);

        log.info("================================");
        log.info("{}", body);
        log.info("================================");
    }
}
