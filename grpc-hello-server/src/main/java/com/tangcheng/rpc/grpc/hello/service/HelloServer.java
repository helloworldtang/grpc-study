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
                .forPort(port)//设置RPC端口号
                .addService(new HelloServiceImpl()) //添加服务实现实例对象
                .build()
                .start();
        System.out.println("Server started,listening on " + port);
        server.awaitTermination();//等待RPC服务器终止，从而停止服务端应用程序
    }

}
