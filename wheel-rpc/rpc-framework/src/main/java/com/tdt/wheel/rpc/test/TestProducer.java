package com.tdt.wheel.rpc.test;

import com.tdt.wheel.rpc.server.ApplicationContext;
import com.tdt.wheel.rpc.server.ServiceConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qrc
 * @description TODO
 * @date 2019/8/26
 */
public class TestProducer {
    public static void main(String[] args) throws Exception {
        String connectionString = "zookeeper://localhost:2181";
        HelloService helloService = new HelloServiceImpl();
        ServiceConfig config = new ServiceConfig(HelloService.class, helloService);
        List<ServiceConfig> serviceConfigList = new ArrayList<>();
        serviceConfigList.add(config);
        ApplicationContext ctx = new ApplicationContext(
                connectionString,
                serviceConfigList,
                50071);
    }
}
