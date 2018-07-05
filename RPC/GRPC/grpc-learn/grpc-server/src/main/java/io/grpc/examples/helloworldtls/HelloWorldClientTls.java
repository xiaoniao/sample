/*
 * Copyright 2015 The gRPC Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.grpc.examples.helloworldtls;

import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;
import io.grpc.examples.helloworld.HelloWorldServer;
import io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;

import javax.net.ssl.SSLException;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A simple client that requests a greeting from the {@link HelloWorldServer} with TLS.
 */
public class HelloWorldClientTls {
    private static final Logger logger = Logger.getLogger(HelloWorldClientTls.class.getName());

    private final ManagedChannel channel;
    private final GreeterGrpc.GreeterBlockingStub blockingStub;

    /**
     * Construct client connecting to HelloWorld server at {@code host:port}.
     */
    public HelloWorldClientTls(String host, int port, SslContext sslContext) throws SSLException {
        this(NettyChannelBuilder.forAddress(host, port).negotiationType(NegotiationType.TLS).sslContext(sslContext).build());
    }

    /**
     * Construct client for accessing RouteGuide server using the existing channel.
     */
    HelloWorldClientTls(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    private static SslContext buildSslContext(String trustCertCollectionFilePath, String clientCertChainFilePath, String clientPrivateKeyFilePath) throws SSLException {
        SslContextBuilder builder = GrpcSslContexts.forClient();
        if (trustCertCollectionFilePath != null) {
            builder.trustManager(new File(trustCertCollectionFilePath));
        }
        if (clientCertChainFilePath != null && clientPrivateKeyFilePath != null) {
            builder.keyManager(new File(clientCertChainFilePath), new File(clientPrivateKeyFilePath));
        }
        return builder.build();
    }

    /**
     * Greet server.
     */
    public static void main(String[] args) throws Exception {

        /*if (args.length < 2 || args.length == 4 || args.length > 5) {
            System.out.println("USAGE: HelloWorldClientTls host port [trustCertCollectionFilePath] " +
                    "[clientCertChainFilePath] [clientPrivateKeyFilePath]\n  Note: clientCertChainFilePath and " +
                    "clientPrivateKeyFilePath are only needed if mutual auth is desired. And if you specify " +
                    "clientCertChainFilePath you must also specify clientPrivateKeyFilePath");
            System.exit(0);
        }*/

        String host = "localhost";
        int port = 8888;
        String trustCertCollectionFilePath = null;
        String clientCertChainFilePath = null;
        String clientPrivateKeyFilePath = null;

        args = new String[] {};

        HelloWorldClientTls client = new HelloWorldClientTls(host, port, buildSslContext(null, null, null));
        HelloWorldClientTls client2 =new HelloWorldClientTls(host, port, buildSslContext(trustCertCollectionFilePath, null, null));
        HelloWorldClientTls client3 =new HelloWorldClientTls(host, port, buildSslContext(trustCertCollectionFilePath, clientCertChainFilePath, clientPrivateKeyFilePath));

        try {
            String user = "world";
            if (args.length > 0) {
                user = args[0]; /* Use the arg as the name to greet if provided */
            }
            client.greet(user);
        } finally {
            client.shutdown();
        }
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     * Say hello to server.
     */
    public void greet(String name) {
        logger.info("Will try to greet " + name + " ...");
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloReply response;
        try {
            response = blockingStub.sayHello(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
        logger.info("Greeting: " + response.getMessage());
    }
}
