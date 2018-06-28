package io.netty.example.definitive.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 *
 */
public final class WebSocketServer {

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
                            p.addLast(new HttpServerCodec());
                            p.addLast(new HttpObjectAggregator(65536));
                            p.addLast(new ChunkedWriteHandler());
                            p.addLast(new WebSocketServerHandler());
                        }
                    });

            ChannelFuture f = b.bind(8007).sync();

            f.channel().closeFuture().sync();

        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private static class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {
        private Logger log = LoggerFactory.getLogger("LZZ-WebSocketServerHandler");

        private WebSocketServerHandshaker handshake;

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Object object) throws Exception {
            if (object instanceof FullHttpRequest) {
                handleHttpRequest(ctx, (FullHttpRequest) object);

            } else if (object instanceof WebSocketFrame) {
                handleWebSocketFrame(ctx, (WebSocketFrame) object);
            }
        }

        private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest httpRequest) {
            if (!httpRequest.decoderResult().isSuccess()) {
                sendHttpResponse(ctx, httpRequest, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
                return;
            }

            if (!httpRequest.headers().get("Upgrade").equals("websocket")) {
                sendHttpResponse(ctx, httpRequest, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
                return;
            }

            WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws://localhost:8007/websocket", null, false);
            handshake = wsFactory.newHandshaker(httpRequest);
            if (handshake == null) {
                WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());

            } else {
                handshake.handshake(ctx.channel(), httpRequest);

            }
        }

        private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame webSocketFrame) {
            if (webSocketFrame instanceof CloseWebSocketFrame) {
                handshake.close(ctx.channel(), (CloseWebSocketFrame) webSocketFrame.retain());
                return;
            }

            if (webSocketFrame instanceof PingWebSocketFrame) {
                ctx.channel().write(new PongWebSocketFrame(webSocketFrame.content().retain()));
                return;
            }

            if (!(webSocketFrame instanceof TextWebSocketFrame)) {
                throw new UnsupportedOperationException();
            }

            TextWebSocketFrame textWebSocketFrame = (TextWebSocketFrame) webSocketFrame;
            String request = textWebSocketFrame.text();

            String result = request + " - " + new Date().toString();
            ctx.channel().write(new TextWebSocketFrame(result));
        }

        private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest request, FullHttpResponse response) {
            if (!response.status().equals(HttpResponseStatus.OK)) {
                ByteBuf bytebuf = Unpooled.copiedBuffer(response.status().toString(), CharsetUtil.UTF_8);
                response.content().writeBytes(bytebuf);
                bytebuf.release();
                HttpUtil.setContentLength(response, response.content().readableBytes());

            }

            ChannelFuture channelFuture = ctx.channel().writeAndFlush(response);
            if (!HttpUtil.isKeepAlive(request) || !response.status().equals(HttpResponseStatus.OK)) {
                channelFuture.addListener(ChannelFutureListener.CLOSE);
            }
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.flush();
        }
    }
}
