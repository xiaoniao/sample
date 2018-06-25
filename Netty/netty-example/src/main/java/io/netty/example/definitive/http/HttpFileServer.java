package io.netty.example.definitive.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.example.definitive.serialize.java.SubscribeReqProto;
import io.netty.example.definitive.serialize.java.SubscribeRespProto;
import io.netty.handler.codec.DecoderResultProvider;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedFile;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GenericProgressiveFutureListener;
import io.netty.util.concurrent.ProgressiveFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 *
 */
public final class HttpFileServer {

    public static void main(String[] args) throws Exception {

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();

                            p.addLast(new HttpRequestDecoder());

                            /*
                             * 将多个消息转换为单一的FullHtpRequest或者 FullHttpResponse
                             */
                            p.addLast(new HttpObjectAggregator(65536));
                            p.addLast(new HttpResponseEncoder());

                            /*
                             * 大文件传输
                             */
                            p.addLast(new ChunkedWriteHandler());
                            p.addLast(new StringDecoder());
                            p.addLast(new StringEncoder());
                            p.addLast(new HttpFileServerHandler());
                        }
                    });

            ChannelFuture f = b.bind(8007).sync();

            f.channel().closeFuture().sync();

        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private static class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
        private Logger log = LoggerFactory.getLogger("LZZ-HttpFileServerHandler");

        private String url = "";

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {

            if (!request.decoderResult().isSuccess()) {
                sendError(ctx, "BAD_REQUEST");
                return;
            }

            if (!request.method().equals(HttpMethod.GET)) {
                sendError(ctx, "METHOD_NOT_ALLOWED");
                return;
            }

            String path = sanitizeUri(request.uri());
            if (path == null) {
                sendError(ctx, "FORBIDDEN");
                return;
            }

            File file = new File(path);
            if (file.isHidden() || !file.exists()) {
                sendError(ctx, "FILE_NOT_FOUNT");
                return;
            }

            if (file.isDirectory()) {
                // ss
                return;
            }

            RandomAccessFile randomAccessFile = null;
            try {
                randomAccessFile = new RandomAccessFile(file, "r");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                sendError(ctx, "FILE_NOT_FOUNT");
                return;
            }

            long fileLength = randomAccessFile.length();

            HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
            ctx.write(response);

            ChannelFuture sendFileFuture = ctx.write(new ChunkedFile(randomAccessFile, 0, fileLength, 8129), ctx.newProgressivePromise());
            sendFileFuture.addListener(new ChannelProgressiveFutureListener() {
                @Override
                public void operationProgressed(ChannelProgressiveFuture channelProgressiveFuture, long progress, long total) throws Exception {
                    log.info("process:{} total:{}", progress, total);
                }

                @Override
                public void operationComplete(ChannelProgressiveFuture channelProgressiveFuture) throws Exception {
                    log.info("complete");
                }
            });

            ChannelFuture lastContentFuture = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
            lastContentFuture.addListener(ChannelFutureListener.CLOSE);

        }

        // TODO
        private String sanitizeUri(String uri) {
            try {
                uri = URLDecoder.decode(uri, CharsetUtil.UTF_8.name());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();

                try {
                    URLDecoder.decode(uri, "ISO-8859-1");
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                    throw new Error();
                }
            }

            if (!uri.startsWith(url)) {
                return null;
            }
            if (!uri.startsWith("/")) {
                return null;
            }
            return uri;
        }

        private void sendError(ChannelHandlerContext ctx, String result) {
            ctx.writeAndFlush(result);
        }
    }
}
