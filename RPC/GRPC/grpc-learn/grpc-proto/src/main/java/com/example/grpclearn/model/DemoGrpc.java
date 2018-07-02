package com.example.grpclearn.model;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 **
 * 指定服务，客户端和服务端的stub, 服务端需要实现接口，客户端直接调用该接口。
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.13.1)",
    comments = "Source: rpc_demo.proto")
public final class DemoGrpc {

  private DemoGrpc() {}

  public static final String SERVICE_NAME = "com.example.grpclearn.model.Demo";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.grpclearn.model.MyDemo.MyRequest,
      com.example.grpclearn.model.MyDemo.MyResponse> getGetUserByIdMethod;

  public static io.grpc.MethodDescriptor<com.example.grpclearn.model.MyDemo.MyRequest,
      com.example.grpclearn.model.MyDemo.MyResponse> getGetUserByIdMethod() {
    io.grpc.MethodDescriptor<com.example.grpclearn.model.MyDemo.MyRequest, com.example.grpclearn.model.MyDemo.MyResponse> getGetUserByIdMethod;
    if ((getGetUserByIdMethod = DemoGrpc.getGetUserByIdMethod) == null) {
      synchronized (DemoGrpc.class) {
        if ((getGetUserByIdMethod = DemoGrpc.getGetUserByIdMethod) == null) {
          DemoGrpc.getGetUserByIdMethod = getGetUserByIdMethod = 
              io.grpc.MethodDescriptor.<com.example.grpclearn.model.MyDemo.MyRequest, com.example.grpclearn.model.MyDemo.MyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.example.grpclearn.model.Demo", "GetUserById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpclearn.model.MyDemo.MyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpclearn.model.MyDemo.MyResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new DemoMethodDescriptorSupplier("GetUserById"))
                  .build();
          }
        }
     }
     return getGetUserByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpclearn.model.MyDemo.InfoRequest,
      com.example.grpclearn.model.MyDemo.InfoResponse> getGetInfosMethod;

  public static io.grpc.MethodDescriptor<com.example.grpclearn.model.MyDemo.InfoRequest,
      com.example.grpclearn.model.MyDemo.InfoResponse> getGetInfosMethod() {
    io.grpc.MethodDescriptor<com.example.grpclearn.model.MyDemo.InfoRequest, com.example.grpclearn.model.MyDemo.InfoResponse> getGetInfosMethod;
    if ((getGetInfosMethod = DemoGrpc.getGetInfosMethod) == null) {
      synchronized (DemoGrpc.class) {
        if ((getGetInfosMethod = DemoGrpc.getGetInfosMethod) == null) {
          DemoGrpc.getGetInfosMethod = getGetInfosMethod = 
              io.grpc.MethodDescriptor.<com.example.grpclearn.model.MyDemo.InfoRequest, com.example.grpclearn.model.MyDemo.InfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.example.grpclearn.model.Demo", "GetInfos"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpclearn.model.MyDemo.InfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpclearn.model.MyDemo.InfoResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new DemoMethodDescriptorSupplier("GetInfos"))
                  .build();
          }
        }
     }
     return getGetInfosMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpclearn.model.MyDemo.GreetRequest,
      com.example.grpclearn.model.MyDemo.GreetResponse> getGreetingMethod;

  public static io.grpc.MethodDescriptor<com.example.grpclearn.model.MyDemo.GreetRequest,
      com.example.grpclearn.model.MyDemo.GreetResponse> getGreetingMethod() {
    io.grpc.MethodDescriptor<com.example.grpclearn.model.MyDemo.GreetRequest, com.example.grpclearn.model.MyDemo.GreetResponse> getGreetingMethod;
    if ((getGreetingMethod = DemoGrpc.getGreetingMethod) == null) {
      synchronized (DemoGrpc.class) {
        if ((getGreetingMethod = DemoGrpc.getGreetingMethod) == null) {
          DemoGrpc.getGreetingMethod = getGreetingMethod = 
              io.grpc.MethodDescriptor.<com.example.grpclearn.model.MyDemo.GreetRequest, com.example.grpclearn.model.MyDemo.GreetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.example.grpclearn.model.Demo", "Greeting"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpclearn.model.MyDemo.GreetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpclearn.model.MyDemo.GreetResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new DemoMethodDescriptorSupplier("Greeting"))
                  .build();
          }
        }
     }
     return getGreetingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpclearn.model.MyDemo.PeopleRequest,
      com.example.grpclearn.model.MyDemo.PeopleList> getGetPeoplesByNameMethod;

  public static io.grpc.MethodDescriptor<com.example.grpclearn.model.MyDemo.PeopleRequest,
      com.example.grpclearn.model.MyDemo.PeopleList> getGetPeoplesByNameMethod() {
    io.grpc.MethodDescriptor<com.example.grpclearn.model.MyDemo.PeopleRequest, com.example.grpclearn.model.MyDemo.PeopleList> getGetPeoplesByNameMethod;
    if ((getGetPeoplesByNameMethod = DemoGrpc.getGetPeoplesByNameMethod) == null) {
      synchronized (DemoGrpc.class) {
        if ((getGetPeoplesByNameMethod = DemoGrpc.getGetPeoplesByNameMethod) == null) {
          DemoGrpc.getGetPeoplesByNameMethod = getGetPeoplesByNameMethod = 
              io.grpc.MethodDescriptor.<com.example.grpclearn.model.MyDemo.PeopleRequest, com.example.grpclearn.model.MyDemo.PeopleList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.example.grpclearn.model.Demo", "GetPeoplesByName"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpclearn.model.MyDemo.PeopleRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpclearn.model.MyDemo.PeopleList.getDefaultInstance()))
                  .setSchemaDescriptor(new DemoMethodDescriptorSupplier("GetPeoplesByName"))
                  .build();
          }
        }
     }
     return getGetPeoplesByNameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpclearn.model.MyDemo.StudentRequest,
      com.example.grpclearn.model.MyDemo.StudentList> getGetStudentsMethod;

  public static io.grpc.MethodDescriptor<com.example.grpclearn.model.MyDemo.StudentRequest,
      com.example.grpclearn.model.MyDemo.StudentList> getGetStudentsMethod() {
    io.grpc.MethodDescriptor<com.example.grpclearn.model.MyDemo.StudentRequest, com.example.grpclearn.model.MyDemo.StudentList> getGetStudentsMethod;
    if ((getGetStudentsMethod = DemoGrpc.getGetStudentsMethod) == null) {
      synchronized (DemoGrpc.class) {
        if ((getGetStudentsMethod = DemoGrpc.getGetStudentsMethod) == null) {
          DemoGrpc.getGetStudentsMethod = getGetStudentsMethod = 
              io.grpc.MethodDescriptor.<com.example.grpclearn.model.MyDemo.StudentRequest, com.example.grpclearn.model.MyDemo.StudentList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.example.grpclearn.model.Demo", "GetStudents"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpclearn.model.MyDemo.StudentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpclearn.model.MyDemo.StudentList.getDefaultInstance()))
                  .setSchemaDescriptor(new DemoMethodDescriptorSupplier("GetStudents"))
                  .build();
          }
        }
     }
     return getGetStudentsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DemoStub newStub(io.grpc.Channel channel) {
    return new DemoStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DemoBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new DemoBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DemoFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new DemoFutureStub(channel);
  }

  /**
   * <pre>
   **
   * 指定服务，客户端和服务端的stub, 服务端需要实现接口，客户端直接调用该接口。
   * </pre>
   */
  public static abstract class DemoImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     **
     * 指定方法，必须以rpc开头，方法名(GetUserById)按照官网所给出的示例代码，均是大写，所以我们也按照这种方式定义。
     *         MyRequest为方法的参数，必须指定，并且必须为message类型。
     *      MyResponse为方法的返回类型，必须制定，并且必须为message类型。
     * </pre>
     */
    public void getUserById(com.example.grpclearn.model.MyDemo.MyRequest request,
        io.grpc.stub.StreamObserver<com.example.grpclearn.model.MyDemo.MyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetUserByIdMethod(), responseObserver);
    }

    /**
     */
    public void getInfos(com.example.grpclearn.model.MyDemo.InfoRequest request,
        io.grpc.stub.StreamObserver<com.example.grpclearn.model.MyDemo.InfoResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetInfosMethod(), responseObserver);
    }

    /**
     * <pre>
     *请求的参数为流
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.example.grpclearn.model.MyDemo.GreetRequest> greeting(
        io.grpc.stub.StreamObserver<com.example.grpclearn.model.MyDemo.GreetResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getGreetingMethod(), responseObserver);
    }

    /**
     * <pre>
     *返回的数据为流
     * </pre>
     */
    public void getPeoplesByName(com.example.grpclearn.model.MyDemo.PeopleRequest request,
        io.grpc.stub.StreamObserver<com.example.grpclearn.model.MyDemo.PeopleList> responseObserver) {
      asyncUnimplementedUnaryCall(getGetPeoplesByNameMethod(), responseObserver);
    }

    /**
     * <pre>
     *请求参数和返回参数都为流
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.example.grpclearn.model.MyDemo.StudentRequest> getStudents(
        io.grpc.stub.StreamObserver<com.example.grpclearn.model.MyDemo.StudentList> responseObserver) {
      return asyncUnimplementedStreamingCall(getGetStudentsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetUserByIdMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpclearn.model.MyDemo.MyRequest,
                com.example.grpclearn.model.MyDemo.MyResponse>(
                  this, METHODID_GET_USER_BY_ID)))
          .addMethod(
            getGetInfosMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpclearn.model.MyDemo.InfoRequest,
                com.example.grpclearn.model.MyDemo.InfoResponse>(
                  this, METHODID_GET_INFOS)))
          .addMethod(
            getGreetingMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                com.example.grpclearn.model.MyDemo.GreetRequest,
                com.example.grpclearn.model.MyDemo.GreetResponse>(
                  this, METHODID_GREETING)))
          .addMethod(
            getGetPeoplesByNameMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.example.grpclearn.model.MyDemo.PeopleRequest,
                com.example.grpclearn.model.MyDemo.PeopleList>(
                  this, METHODID_GET_PEOPLES_BY_NAME)))
          .addMethod(
            getGetStudentsMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.example.grpclearn.model.MyDemo.StudentRequest,
                com.example.grpclearn.model.MyDemo.StudentList>(
                  this, METHODID_GET_STUDENTS)))
          .build();
    }
  }

  /**
   * <pre>
   **
   * 指定服务，客户端和服务端的stub, 服务端需要实现接口，客户端直接调用该接口。
   * </pre>
   */
  public static final class DemoStub extends io.grpc.stub.AbstractStub<DemoStub> {
    private DemoStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DemoStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DemoStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DemoStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * 指定方法，必须以rpc开头，方法名(GetUserById)按照官网所给出的示例代码，均是大写，所以我们也按照这种方式定义。
     *         MyRequest为方法的参数，必须指定，并且必须为message类型。
     *      MyResponse为方法的返回类型，必须制定，并且必须为message类型。
     * </pre>
     */
    public void getUserById(com.example.grpclearn.model.MyDemo.MyRequest request,
        io.grpc.stub.StreamObserver<com.example.grpclearn.model.MyDemo.MyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetUserByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getInfos(com.example.grpclearn.model.MyDemo.InfoRequest request,
        io.grpc.stub.StreamObserver<com.example.grpclearn.model.MyDemo.InfoResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetInfosMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *请求的参数为流
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.example.grpclearn.model.MyDemo.GreetRequest> greeting(
        io.grpc.stub.StreamObserver<com.example.grpclearn.model.MyDemo.GreetResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getGreetingMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     *返回的数据为流
     * </pre>
     */
    public void getPeoplesByName(com.example.grpclearn.model.MyDemo.PeopleRequest request,
        io.grpc.stub.StreamObserver<com.example.grpclearn.model.MyDemo.PeopleList> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetPeoplesByNameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *请求参数和返回参数都为流
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.example.grpclearn.model.MyDemo.StudentRequest> getStudents(
        io.grpc.stub.StreamObserver<com.example.grpclearn.model.MyDemo.StudentList> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getGetStudentsMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   **
   * 指定服务，客户端和服务端的stub, 服务端需要实现接口，客户端直接调用该接口。
   * </pre>
   */
  public static final class DemoBlockingStub extends io.grpc.stub.AbstractStub<DemoBlockingStub> {
    private DemoBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DemoBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DemoBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DemoBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * 指定方法，必须以rpc开头，方法名(GetUserById)按照官网所给出的示例代码，均是大写，所以我们也按照这种方式定义。
     *         MyRequest为方法的参数，必须指定，并且必须为message类型。
     *      MyResponse为方法的返回类型，必须制定，并且必须为message类型。
     * </pre>
     */
    public com.example.grpclearn.model.MyDemo.MyResponse getUserById(com.example.grpclearn.model.MyDemo.MyRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetUserByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpclearn.model.MyDemo.InfoResponse getInfos(com.example.grpclearn.model.MyDemo.InfoRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetInfosMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *返回的数据为流
     * </pre>
     */
    public java.util.Iterator<com.example.grpclearn.model.MyDemo.PeopleList> getPeoplesByName(
        com.example.grpclearn.model.MyDemo.PeopleRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getGetPeoplesByNameMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   **
   * 指定服务，客户端和服务端的stub, 服务端需要实现接口，客户端直接调用该接口。
   * </pre>
   */
  public static final class DemoFutureStub extends io.grpc.stub.AbstractStub<DemoFutureStub> {
    private DemoFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DemoFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DemoFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DemoFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * 指定方法，必须以rpc开头，方法名(GetUserById)按照官网所给出的示例代码，均是大写，所以我们也按照这种方式定义。
     *         MyRequest为方法的参数，必须指定，并且必须为message类型。
     *      MyResponse为方法的返回类型，必须制定，并且必须为message类型。
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpclearn.model.MyDemo.MyResponse> getUserById(
        com.example.grpclearn.model.MyDemo.MyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetUserByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpclearn.model.MyDemo.InfoResponse> getInfos(
        com.example.grpclearn.model.MyDemo.InfoRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetInfosMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_USER_BY_ID = 0;
  private static final int METHODID_GET_INFOS = 1;
  private static final int METHODID_GET_PEOPLES_BY_NAME = 2;
  private static final int METHODID_GREETING = 3;
  private static final int METHODID_GET_STUDENTS = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DemoImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(DemoImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_USER_BY_ID:
          serviceImpl.getUserById((com.example.grpclearn.model.MyDemo.MyRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpclearn.model.MyDemo.MyResponse>) responseObserver);
          break;
        case METHODID_GET_INFOS:
          serviceImpl.getInfos((com.example.grpclearn.model.MyDemo.InfoRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpclearn.model.MyDemo.InfoResponse>) responseObserver);
          break;
        case METHODID_GET_PEOPLES_BY_NAME:
          serviceImpl.getPeoplesByName((com.example.grpclearn.model.MyDemo.PeopleRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpclearn.model.MyDemo.PeopleList>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GREETING:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.greeting(
              (io.grpc.stub.StreamObserver<com.example.grpclearn.model.MyDemo.GreetResponse>) responseObserver);
        case METHODID_GET_STUDENTS:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.getStudents(
              (io.grpc.stub.StreamObserver<com.example.grpclearn.model.MyDemo.StudentList>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class DemoBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DemoBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.grpclearn.model.MyDemo.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Demo");
    }
  }

  private static final class DemoFileDescriptorSupplier
      extends DemoBaseDescriptorSupplier {
    DemoFileDescriptorSupplier() {}
  }

  private static final class DemoMethodDescriptorSupplier
      extends DemoBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    DemoMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (DemoGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DemoFileDescriptorSupplier())
              .addMethod(getGetUserByIdMethod())
              .addMethod(getGetInfosMethod())
              .addMethod(getGreetingMethod())
              .addMethod(getGetPeoplesByNameMethod())
              .addMethod(getGetStudentsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
