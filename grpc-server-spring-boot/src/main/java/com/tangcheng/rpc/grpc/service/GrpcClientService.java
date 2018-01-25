package com.tangcheng.rpc.grpc.service;

import com.tangcheng.rpc.grpc.client.GrpcClient;
import com.tangcheng.rpc.grpc.hello.api.HelloRequest;
import com.tangcheng.rpc.grpc.hello.api.HelloResponse;
import com.tangcheng.rpc.grpc.hello.api.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @author tangcheng
 * 2018/01/25
 */
@Slf4j
@Service
@Order(2)
public class GrpcClientService implements CommandLineRunner {

    @Autowired
    private GrpcClient grpcClient;

    @Override
    public void run(String... args) throws Exception {
        ManagedChannel channel = grpcClient.buildChannel();
        try {
            HelloServiceGrpc.HelloServiceBlockingStub helloService = HelloServiceGrpc.newBlockingStub(channel);
            HelloRequest request = HelloRequest
                    .newBuilder()
                    .setName("world,spring-boot")
                    .build();
            HelloResponse response = helloService.say(request);
            log.info("client : {} ", response.getMessage());
        } finally {
            channel.shutdown();
        }
    }

}
