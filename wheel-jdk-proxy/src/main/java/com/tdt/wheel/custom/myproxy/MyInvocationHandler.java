package com.tdt.wheel.custom.myproxy;

import java.lang.reflect.Method;

/**
 * description: MyInvocationHandler
 *
 * @date: 2020年11月12日 16:50
 * @author: qinrenchuan
 */
public interface MyInvocationHandler {
    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
