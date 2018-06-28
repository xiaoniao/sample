//package io.netty.example.definitive.book;
//
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelInboundHandlerAdapter;
//import io.netty.example.definitive.serialize.java.SubscribeReqProto;
//import io.netty.example.definitive.serialize.java.SubscribeRespProto;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.SynchronousQueue;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by liuzz on 2018/06/27
// */
//public class BusinessHandler extends ChannelInboundHandlerAdapter {
//    private Logger log = LoggerFactory.getLogger("LZZ-BusinessHandler");
//
//    private static ExecutorService executorService = newBlockingExecutors(Runtime.getRuntime().availableProcessors() * 2);
//
//    private static ExecutorService newBlockingExecutors(int size) {
//        return new ThreadPoolExecutor(size, size, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<>(), (runnable, executor) -> {
//            try {
//                executor.getQueue().put(runnable);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//    }
//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        log.info("======BusinessHandler");
//        if(msg instanceof SubscribeReqProto.SubscribeReq) {
//            executorService.submit(() -> {
//                SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq) msg;
//
//                SubscribeRespProto.SubscribeResp.Builder builder = SubscribeRespProto.SubscribeResp.newBuilder();
//                builder.setSubReqID(1);
//                builder.setRespCode(1);
//                builder.setDesc("business-goods");
//                ctx.writeAndFlush(builder.build());
//            });
//        }
//    }
//}
