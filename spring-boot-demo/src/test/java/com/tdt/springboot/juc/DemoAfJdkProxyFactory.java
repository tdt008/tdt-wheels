package com.tdt.springboot.juc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * description: DemoAfJdkProxyFactory
 *
 * @date: 2020年11月05日 20:40
 * @author: qinrenchuan
 */
public class DemoAfJdkProxyFactory implements InvocationHandler {

    private Object targetObj;

    public DemoAfJdkProxyFactory(Object targetObj) {
        super();
        this.targetObj = targetObj;
    }

    public Object createProxy() {
        ClassLoader classLoader = targetObj.getClass().getClassLoader();
        Class<?>[] interfaces = targetObj.getClass().getInterfaces();
        return Proxy.newProxyInstance(classLoader, interfaces, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("myproxy before");
        Object invokeObj = method.invoke(targetObj, args);
        System.out.println("myproxy after");
        return invokeObj;
    }

    public static void main(String[] args) {
        MyHelloServiceImpl myHelloService = new MyHelloServiceImpl();
        DemoAfJdkProxyFactory proxyFactory = new DemoAfJdkProxyFactory(myHelloService);

        HelloService helloService = (HelloService) proxyFactory.createProxy();
        helloService.sayHello();
    }
}


interface HelloService {
    void sayHello();
}

class MyHelloServiceImpl implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("MyHelloServiceImpl say hello");
    }
}
