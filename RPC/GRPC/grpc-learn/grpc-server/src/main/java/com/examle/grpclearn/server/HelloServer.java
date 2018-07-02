package com.examle.grpclearn.server;

import com.example.grpclearn.model.GreeterGrpc;
import com.example.grpclearn.model.HelloProto;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by liuzz on 2018/07/02
 */
class HelloServer {

    private static final Logger logger = LoggerFactory.getLogger(HelloServer.class);

    private static final int port = 50051;

    private Server server;

    void start() throws IOException, InterruptedException {
        server = ServerBuilder.forPort(port)
                .addService(new GreeterImpl())
                .build()
                .start();
        logger.info("Server started, listening on " + port);

        blockUntilShutdown();
    }

    private class GreeterImpl extends GreeterGrpc.GreeterImplBase {

        @Override
        public void sayHello(HelloProto.HelloRequest req, StreamObserver<HelloProto.HelloReply> responseObserver) {
            HelloProto.HelloReply reply = HelloProto.HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}