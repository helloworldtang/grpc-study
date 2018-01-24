package com.tangcheng.rpc.grpc.hello.service;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * @author tangcheng
 * 2018/01/24
 */
public class HelloServer {

    private static final int port = 8000;

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder
                .forPort(port)
                .addService(new HelloServiceImpl())
                .build()
                .start();
        System.out.println("Server started,listening on " + port);
        server.awaitTermination();
    }

}
