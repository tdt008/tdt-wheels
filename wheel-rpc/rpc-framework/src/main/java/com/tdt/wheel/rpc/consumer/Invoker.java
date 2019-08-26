package com.tdt.wheel.rpc.consumer;

/**
 * @author qrc
 * @description TODO
 * @date 2019/8/26
 */
public interface Invoker<T> {

    T invoke(Object[] args);

    void setResult(String result);
}
