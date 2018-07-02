package com.example.grpclearn.client;


import com.example.grpclearn.model.DemoGrpc;
import com.example.grpclearn.model.GreeterGrpc;
import com.example.grpclearn.model.HelloProto;
import com.example.grpclearn.model.MyDemo;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by liuzz on 2018/07/02
 */
class HelloClient {
    private static final Logger logger = LoggerFactory.getLogger(HelloClient.class);

    private final ManagedChannel channel;

    private final GreeterGrpc.GreeterBlockingStub blockingStub;

    private final DemoGrpc.DemoBlockingStub demoBlockingStub;

    private final DemoGrpc.DemoStub demoStub;

    private final DemoGrpc.DemoFutureStub demoFutureStub;

    /**
     * Construct client connecting to HelloWorld server at {@code host:port}.
     */
    HelloClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();

        blockingStub = GreeterGrpc.newBlockingStub(channel);

        demoBlockingStub = DemoGrpc.newBlockingStub(channel);
        demoStub = DemoGrpc.newStub(channel);
        demoFutureStub = DemoGrpc.newFutureStub(channel);
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

    public void getUserById() {
        MyDemo.MyResponse resp = this.demoBlockingStub.getUserById(MyDemo.MyRequest.newBuilder().setId(80000).build());
        logger.info("服务端返回的数据: {}", resp.getRealname());
    }

    public void getInfos() {
        MyDemo.InfoResponse resp = this.demoBlockingStub.getInfos(MyDemo.InfoRequest.newBuilder().setMsg("信息一").build());
        resp.getInfosList().forEach(info -> {
            logger.info(info.getAge() + ":" + info.getName() + ":" + info.getFlag());
            info.getOthersMap().forEach((k, v) -> logger.info(k + " == " + v));
        });
    }

    public void greeting() {
        StreamObserver<MyDemo.GreetRequest> requestStream = demoStub.greeting(new StreamObserver<MyDemo.GreetResponse>() {

            @Override
            public void onNext(MyDemo.GreetResponse resp) {
                logger.info("接收到的数据：data:{}, msg:{}", resp.getDate(), resp.getMsg());
            }

            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onCompleted() {
                logger.info("服务端的onComplete()方法执行完毕");
            }
        });

        for (int i = 0; i < 10; i++) {
            requestStream.onNext(MyDemo.GreetRequest.newBuilder().setName("张三" + i).build());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        requestStream.onCompleted();
    }

    public void getPeoplesByName() {
        MyDemo.PeopleRequest req = MyDemo.PeopleRequest.newBuilder().setName("刘XX").build();

        demoStub.getPeoplesByName(req, new StreamObserver<MyDemo.PeopleList>() {

            @Override
            public void onCompleted() {
                logger.info("数据读取完毕");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(MyDemo.PeopleList peopleList) {
                List<MyDemo.People> list = peopleList.getPeoplesList();
                list.forEach(p -> logger.info(p.getAge() +
                        "" + p.getHeight() +
                        ":" + p.getMoney() +
                        ":" + p.getIsMarried() +
                        ":" + p.getName()));
            }
        });
    }

    public void getStudentsList() {
        StreamObserver<MyDemo.StudentRequest> requestStream = demoStub.getStudents(new StreamObserver<MyDemo.StudentList>() {

            @Override
            public void onCompleted() {
                logger.info("Client onCompleted 结束");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(MyDemo.StudentList studentList) {
                for (MyDemo.Student s : studentList.getStudentsList()) {
                    logger.info("Client 接收到的数据：name:{} score:{}" , s.getName(), s.getScore());
                }
            }
        });

        for (int i = 0; i < 10; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("xx", "XX" + i);
            map.put("zz", "ZZ" + i);
            requestStream.onNext(MyDemo.StudentRequest.newBuilder().putAllInfos(map).build());
            logger.info("Client sendMsg");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        logger.info("#####客户端结束发送消息");
        requestStream.onCompleted();
    }
}
