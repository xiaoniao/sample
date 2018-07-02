package com.example.grpclearn.client;


import com.example.grpclearn.model.GreeterGrpc;
import com.example.grpclearn.model.HelloProto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by liuzz on 2018/07/02
 */
class HelloClient {
    private static final Logger logger = LoggerFactory.getLogger(HelloClient.class);

    private final ManagedChannel channel;

    private final GreeterGrpc.GreeterBlockingStub blockingStub;

    /**
     * Construct client connecting to HelloWorld server at {@code host:port}.
     */
    HelloClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();

        blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     * Say hello to server.
     */
    void greet(String name) {
        logger.info("Will try to greet " + name + " ...");
        HelloProto.HelloRequest request = HelloProto.HelloRequest.newBuilder().setName(name).build();
        HelloProto.HelloReply response;
        try {
            response = blockingStub.sayHello(request);
        } catch (StatusRuntimeException e) {
            logger.info("", e);
            return;
        }
        logger.info("Greeting: " + response.getMessage());
    }
}
