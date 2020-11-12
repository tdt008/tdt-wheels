package com.tdt.springboot.juc;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * description: DemoAgCglibProxyFactory
 *
 * @date: 2020年11月05日 20:51
 * @author: qinrenchuan
 */
public class DemoAgCglibProxyFactory implements MethodInterceptor {

    private Object targetObj;

    public DemoAgCglibProxyFactory(Object targetObj) {
        super();
        this.targetObj = targetObj;
    }

    public Object createProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(this);
        enhancer.setSuperclass(targetObj.getClass());
        Object o = enhancer.create();
        return o;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib myproxy before");
        Object invoke = methodProxy.invoke(targetObj, args);
        System.out.println("cglib myproxy after");
        return invoke;
    }

    public static void main(String[] args) {
        MyHelloCglibServiceImpl myHelloCglibService = new MyHelloCglibServiceImpl();
        DemoAgCglibProxyFactory cglibProxyFactory = new DemoAgCglibProxyFactory(myHelloCglibService);

        HelloCglibService helloCglibService = (HelloCglibService) cglibProxyFactory.createProxy();
        helloCglibService.sayHello();
    }
}


interface HelloCglibService {
    void sayHello();
}

class MyHelloCglibServiceImpl implements HelloCglibService {
    @Override
    public void sayHello() {
        System.out.println("MyHelloCglibServiceImpl say hello");
    }
}
