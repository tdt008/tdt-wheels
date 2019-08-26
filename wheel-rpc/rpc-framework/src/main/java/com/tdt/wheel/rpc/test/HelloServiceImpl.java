package com.tdt.wheel.rpc.test;

/**
 * @author qrc
 * @description TODO
 * @date 2019/8/26
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(TestBean testBean) {
        return "牛逼,我收到了消息：" + testBean;
    }
}
