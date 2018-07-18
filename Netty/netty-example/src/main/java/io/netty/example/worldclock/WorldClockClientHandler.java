/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.netty.example.worldclock;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.example.worldclock.WorldClockProtocol.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Formatter;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Pattern;

public class WorldClockClientHandler extends SimpleChannelInboundHandler<LocalTimes> {
    private static final Logger log = LoggerFactory.getLogger(WorldClockClientHandler.class);

    private static final Pattern DELIM = Pattern.compile("/");

    // Stateful properties
    private volatile Channel channel;
    private final BlockingQueue<LocalTimes> answer = new LinkedBlockingQueue<>();

    WorldClockClientHandler() {
        super(false);
    }


    /**
     * 发送消息给服务端，并等待服务端返回数据。
     * @param cities
     * @return
     */
    List<String> getLocalTimes(Collection<String> cities) {

        // 客户端发送数据
        Locations.Builder builder = Locations.newBuilder();
        for (String c : cities) {
            String[] components = DELIM.split(c);
            builder.addLocation(Location.newBuilder().setContinent(Continent.valueOf(components[0].toUpperCase())).setCity(components[1]).build());
        }
        channel.writeAndFlush(builder.build());

        LocalTimes localTimes;
        boolean interrupted = false;
        for (; ; ) {
            try {
                localTimes = answer.take();
                break;
            } catch (InterruptedException ignore) {
                interrupted = true;
            }
        }
        log.info("get form queue");

        if (interrupted) {
            Thread.currentThread().interrupt();
        }

        List<String> result = new ArrayList<>();
        for (LocalTime lt : localTimes.getLocalTimeList()) {
            result.add(new Formatter().format("%4d-%02d-%02d %02d:%02d:%02d %s",
                            lt.getYear(),
                            lt.getMonth(),
                            lt.getDayOfMonth(),
                            lt.getHour(),
                            lt.getMinute(),
                            lt.getSecond(),
                            lt.getDayOfWeek().name()).toString());
        }
        return result;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        channel = ctx.channel();
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, LocalTimes times) throws Exception {
        log.info("channelRead0");
        answer.add(times);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
