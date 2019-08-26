package com.tdt.wheel.rpc.test;/**
 * Created by rc on 2019/8/26.
 */

import com.tdt.wheel.rpc.consumer.ReferenceConfig;
import com.tdt.wheel.rpc.server.ApplicationContext;

import java.util.Collections;

/**
 * @author qrc
 * @description
 * @date 2019/8/26
 */
public class TestConsumer {
    public static void main(String[] args) throws Exception {
        String connectionString = "zookeeper://localhost:2181";
        ReferenceConfig config = new ReferenceConfig(HelloService.class);
        ApplicationContext ctx = new ApplicationContext(
                connectionString,
                null,
                Collections.singletonList(config),
                50070);
        HelloService helloService = (HelloService) ctx.getService(HelloService.class);
        System.out.println("sayHello(TestBean)结果为：" + helloService.sayHello(new TestBean("张三", 20)));
    }
}
