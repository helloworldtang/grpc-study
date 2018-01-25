package com.tangcheng.rpc.grpc.hello.client;

import com.tangcheng.rpc.grpc.hello.api.HelloRequest;
import com.tangcheng.rpc.grpc.hello.api.HelloResponse;
import com.tangcheng.rpc.grpc.hello.api.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * @author tangcheng
 * 2018/01/24
 */
public class HelloClient {

    private static final String host = "localhost";
    private static final int port = 8000;

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext(true)//使用纯文本方式进行数据传输
                .build();

        try {
            HelloServiceGrpc.HelloServiceBlockingStub helloServiceStub = HelloServiceGrpc.newBlockingStub(channel);//使用阻塞式的Stub
            HelloRequest request = HelloRequest
                    .newBuilder()
                    .setName("world")
                    .build();
            HelloResponse response = helloServiceStub.say(request);
            System.out.println(response.getMessage());
        } finally {
            channel.shutdown();//停止RPC客户端应用程序
        }

    }

}
