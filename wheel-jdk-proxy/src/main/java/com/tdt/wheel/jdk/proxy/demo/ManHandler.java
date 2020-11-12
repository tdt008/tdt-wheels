package com.tdt.wheel.jdk.proxy.demo;

import com.tdt.wheel.custom.myproxy.Man;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * description: ManHandler
 *
 * @date: 2020年11月12日 15:26
 * @author: qinrenchuan
 */
public class ManHandler implements InvocationHandler {

    private Man man;

    public ManHandler(Man man) {
        this.man = man;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        method.invoke(man, null);
        after();
        return null;
    }

    public void before() {
        System.out.println("找到pretty girl之前,,,,,,,");
    }

    public void after() {
        System.out.println("找到pretty girl之后,生个娃吧！");
    }
}
