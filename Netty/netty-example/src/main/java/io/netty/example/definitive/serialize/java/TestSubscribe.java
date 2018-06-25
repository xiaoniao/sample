package io.netty.example.definitive.serialize.java;

import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 测试 ProtoBuf
 *
 * Created by liuzz on 2018/06/25
 */
public class TestSubscribe {
    private static Logger log = LoggerFactory.getLogger(TestSubscribe.class);


    public static void main(String[] args) {
        SubscribeReqProto.SubscribeReq subscribeReq = createSubscribeReq();
        log.info(subscribeReq.getUserName());

        byte[] bytes = encode(subscribeReq);

        try {
            SubscribeReqProto.SubscribeReq copy = decode(bytes);
            log.info(subscribeReq.getUserName());

        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解码
     *
     * @param bytes
     * @return
     * @throws InvalidProtocolBufferException
     */
    private static SubscribeReqProto.SubscribeReq decode(byte[] bytes) throws InvalidProtocolBufferException {
        return SubscribeReqProto.SubscribeReq.parseFrom(bytes);
    }

    /**
     * 编码
     *
     * @param subscribeReq
     * @return
     */
    private static byte[] encode(SubscribeReqProto.SubscribeReq subscribeReq) {
        return subscribeReq.toByteArray();
    }

    /**
     * 实例化对象
     *
     * @return
     */
    private static SubscribeReqProto.SubscribeReq createSubscribeReq() {
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqID(1);
        builder.setUserName("jack");
        builder.setProductName("taoBao");
        builder.setAddress("hz");
        return builder.build();
    }
}
