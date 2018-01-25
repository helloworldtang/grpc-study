
package com.tangcheng.rpc.grpc.hello.service;

import com.tangcheng.rpc.grpc.hello.api.HelloRequest;
import com.tangcheng.rpc.grpc.hello.api.HelloResponse;
import com.tangcheng.rpc.grpc.hello.api.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 * @author tangcheng
 * 2018/01/24
 */
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void say(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        HelloResponse response = null;
        try {
            response = HelloResponse.newBuilder()
                    .setMessage("hello " + request.getName())
                    .build();
        } catch (Exception e) {
            responseObserver.onError(e);
        } finally {
            responseObserver.onNext(response);//将HelloResponse对象添加到StreamObserver参数中
        }
        responseObserver.onCompleted();//结束整个响应过程
    }

}
