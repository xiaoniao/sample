package com.examle.grpclearn.server;

import com.example.grpclearn.model.DemoGrpc;
import com.example.grpclearn.model.GreeterGrpc;
import com.example.grpclearn.model.HelloProto;
import com.example.grpclearn.model.MyDemo;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
                .addService(new DemoServiceImpl())
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

    private class DemoServiceImpl extends DemoGrpc.DemoImplBase {


        @Override
        public void getUserById(MyDemo.MyRequest request, StreamObserver<MyDemo.MyResponse> responseObserver) {
            logger.info("接收到的参数：", request.getId());
            responseObserver.onNext(MyDemo.MyResponse.newBuilder().setRealname("嘟嘟").build());
            responseObserver.onCompleted();
        }

        @Override
        public void getInfos(MyDemo.InfoRequest request, StreamObserver<MyDemo.InfoResponse> responseObserver) {
            logger.info("接收到的参数：", request.getMsg());

            Map<Long, String> otherValues = new HashMap<>();
            otherValues.put(1L, "A");
            otherValues.put(2L, "B");
            MyDemo.Info info = MyDemo.Info.newBuilder().setAge(111).setName("嘟嘟").setFlag(false).putAllOthers(otherValues).build();


            Map<Long, String> otherValues1 = new HashMap<>();
            otherValues.put(3L, "C");
            otherValues.put(4L, "D");
            MyDemo.Info info2 = MyDemo.Info.newBuilder().setAge(222).setName("嘟嘟2").setFlag(false).putAllOthers(otherValues1).build();

            responseObserver.onNext(MyDemo.InfoResponse.newBuilder().addAllInfos(Arrays.asList(info, info2)).build());
            responseObserver.onCompleted();
        }

        @Override
        public StreamObserver<MyDemo.GreetRequest> greeting(StreamObserver<MyDemo.GreetResponse> responseObserver) {

            return new StreamObserver<MyDemo.GreetRequest>() {
                @Override
                public void onNext(MyDemo.GreetRequest greetRequest) {
                    logger.info("接受到的数据：{}", greetRequest.getName());
                }

                @Override
                public void onError(Throwable throwable) {

                }

                @Override
                public void onCompleted() {
                    responseObserver.onNext(MyDemo.GreetResponse.newBuilder().setMsg("jack").setDate(String.valueOf(new Date())).build());
                    responseObserver.onCompleted();
                }
            };
        }

        @Override
        public void getPeoplesByName(MyDemo.PeopleRequest request, StreamObserver<MyDemo.PeopleList> responseObserver) {
            logger.info("接受到的数据：", request.getName());

            MyDemo.People p1 = MyDemo.People.newBuilder().setName("张三").setHeight(1).setMoney(1).setIsMarried(true).build();
            MyDemo.People p2 = MyDemo.People.newBuilder().setName("张五").setHeight(2).setMoney(2).setIsMarried(true).build();
            MyDemo.People p3 = MyDemo.People.newBuilder().setName("张六").setHeight(3).setMoney(3).setIsMarried(true).build();
            // responseObserver.onNext(MyDemo.PeopleList.newBuilder().addAllPeoples(Arrays.asList(p1, p2, p3)).build());

            for (int i = 0; i < 10; i++) {
                MyDemo.People p = MyDemo.People.newBuilder().setName("张三" + i).setHeight(1).setMoney(1).setIsMarried(true).build();
                responseObserver.onNext(MyDemo.PeopleList.newBuilder().addPeoples(p).build());
                logger.info("####onNext");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            responseObserver.onCompleted();
        }

        @Override
        public StreamObserver<MyDemo.StudentRequest> getStudents(StreamObserver<MyDemo.StudentList> responseObserver) {

            return new StreamObserver<MyDemo.StudentRequest>() {
                @Override
                public void onNext(MyDemo.StudentRequest studentRequest) {
                    Map<String, String> map = studentRequest.getInfosMap();
                    logger.info("Server 接受到的数据：{}", map.get("xx"));
                }

                @Override
                public void onError(Throwable throwable) {

                }

                @Override
                public void onCompleted() {
                    for (int i = 0; i < 10; i++) {
                        MyDemo.Student s1 = MyDemo.Student.newBuilder().setName("张三" + i).setScore(1).build();
                        MyDemo.Student s2 = MyDemo.Student.newBuilder().setName("张嘎" + i).setScore(1).build();
                        responseObserver.onNext(MyDemo.StudentList.newBuilder().addAllStudents(Arrays.asList(s1, s2)).build());
                        logger.info("Server sendMsg");

                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    logger.info("#####服务端结束发送消息");
                    responseObserver.onCompleted();
                }
            };
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