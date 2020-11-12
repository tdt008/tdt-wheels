package com.tdt.wheel.custom.myproxy;

import java.lang.reflect.Method;

/**
 * description: MyHandler
 *
 * @date: 2020年11月12日 17:59
 * @author: qinrenchuan
 */
public class MyHandler implements MyInvocationHandler {

    private Man man;

    public MyHandler(Man man) {
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
        System.out.println("MyHandler-找到pretty girl之前,,,,,,,");
    }

    public void after() {
        System.out.println("MyHandler-找到pretty girl之后,生个娃吧！");
    }
}
