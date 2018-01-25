package com.tangcheng.rpc.grpc.service;

import com.tangcheng.rpc.grpc.server.GrpcService;
import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author tangcheng
 * 2018/01/25
 */
@Slf4j
@Component
@Order(1)
public class GrpcServerService implements CommandLineRunner {

    @Value("${grpc.port}")
    private int port;

    @Autowired
    private ApplicationContext applicationContext;

    @Async
    @Override
    public void run(String... args) throws Exception {
        //绑定RPC端口号，并添加所有RPC接口实现类实例，最后启动RPC服务器
        List<BindableService> serviceBeanList = new ArrayList<>();
        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(GrpcService.class);
        if (serviceBeanMap != null && serviceBeanMap.size() > 0) {
            for (Object serviceBean : serviceBeanMap.values()) {
                if (serviceBean instanceof BindableService) {
                    serviceBeanList.add((BindableService) serviceBean);
                }
            }
        }
        ServerBuilder builder = ServerBuilder.forPort(port);
        for (BindableService bindableService : serviceBeanList) {
            builder.addService(bindableService);
        }
        Server server = builder.build().start();
        log.info("server started,listening on {} ", port);
        server.awaitTermination();
    }

}
