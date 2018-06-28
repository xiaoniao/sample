package io.netty.example.definitive.futurepromise;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * Future.java
 *
 *   isSuccess	            任务是否执行成功
 *   isCancellable	        任务是否可以取消
 *   cause	                任务产生的异常
 *   addListener	        添加listener, 任务完成后执行listener，如果任务已经完成，则添加时立刻执行
 *   removeListener	        移除listener
 *   sync	                等待任务结束，如果任务产生异常或被中断则抛出异常，否则返回Future自身
 *   syncUninterruptibly	等待任务结束，任务本身不可中断，如果产生异常则抛出异常，否则返回Future自身
 *   await	                等待任务结束，如果任务被中断则抛出中断异常，与sync不同的是只抛出中断异常，不抛出任务产生的异常
 *   awaitUninterruptibly 	等待任务结束，任务不可中断
 *   getNow    	            任务未完成或者发生异常则返回null, 否则返回任务的结果
 *
 *  Future本身不能由实现者直接标记成功或失败，而是由调用的线程来标记。而Promise则在Future的基础上增加了让用户自己标记成功或失败的接口：
 *
 * Promise.java
 *
 *   setSuccess	            通过设置结果的方式标记Future成功并通知所有listener, 如果已被标记过，则抛出异常
 *   trySuccess	            通过设置结果的方式标记Future成功并通知所有listener, 如果已被标记过，只是返回false
 *   setFailure    	        通过设置异常的方式标记Future失败并通知所有listener, 如果已被标记过，则抛出异常
 *   tryFailure    	        通过设置异常的方式标记Future失败并通知所有listener, 如果已被标记过，只是返回false
 *
 * DefaultPromise.jaa
 *
 *   result
 *         null	                        任务还未开始执行（初始值），此时任务可以被取消
 *         UNCANCELLABLE	            任务不可取消
 *         CANCELLATION_CAUSE_HOLDER	任务取消
 *         CauseHolder	                执行完成，产生的异常
 *         SUCCESS	                    执行成功，结果为null
 *         其他	                        执行成功，结果为result
 *
 *
 * ChannelFuture 用于获取异步操作结果
 *
 *      两种状态:
 *      uncompleted
 *      completed
 *          completed successfully
 *          completed with failure
 *          completed by cancellation
 *
 *
 */
public final class FutureAndPromiseServer {

    public static void main(String[] args) throws Exception {

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000) // TCP I/O 超时时间
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();

                            p.addLast(new HttpRequestDecoder());
                            p.addLast(new HttpObjectAggregator(65536));
                            p.addLast(new HttpResponseEncoder());
                            p.addLast(new ChunkedWriteHandler());
                            p.addLast(new StringDecoder());
                            p.addLast(new StringEncoder());
                            p.addLast(new HttpServerHandler());
                        }
                    });

            ChannelFuture f = b.bind(8007).sync();


            f.channel().closeFuture().sync();

        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private static class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
        private Logger log = LoggerFactory.getLogger("LZZ-HttpServerHandler");

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
            log.info("uri:{} method:{}", request.uri(), request.method());

            HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
            ctx.write(response);

            /*
             * Netty 建议通过添加监听器的方式获取 I/O 操作结果
             */
            // DefaultChannelProgressivePromise DefaultPromise子类
            ChannelFuture sendMessageFuture = ctx.write("Hello World!!!!", ctx.newProgressivePromise());
            sendMessageFuture.addListener(new ChannelProgressiveFutureListener() {
                @Override
                public void operationProgressed(ChannelProgressiveFuture channelProgressiveFuture, long progress, long total) throws Exception {
                    log.info("process:{} total:{}", progress, total);
                }

                @Override
                public void operationComplete(ChannelProgressiveFuture channelProgressiveFuture) throws Exception {
                    log.info("complete url:{} future:{}", request.uri(), channelProgressiveFuture.getNow());
                }
            });


            // AbstractChannelHandlerContext new DefaultChannelPromise(channel(), executor())
            sendMessageFuture.addListener((ChannelFutureListener) channelFuture -> log.info("complete"));
            ChannelFuture lastContentFuture = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
            lastContentFuture.addListener(ChannelFutureListener.CLOSE);
        }
    }
}
