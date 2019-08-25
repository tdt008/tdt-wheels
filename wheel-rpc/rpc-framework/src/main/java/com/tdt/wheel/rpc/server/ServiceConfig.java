package com.tdt.wheel.rpc.server;/**
 * Created by rc on 2019/8/25.
 */

/**
 * @author qrc
 * @description ServiceConfig
 * @date 2019/8/25
 */
public  class ServiceConfig<T> {

    public Class type;

    public T instance;

    public ServiceConfig(Class type, T instance) {
        this.type = type;
        this.instance = instance;
    }

    public Class getType() {
        return type;
    }

    public ServiceConfig setType(Class type) {
        this.type = type;
        return this;
    }

    public T getInstance() {
        return instance;
    }

    public ServiceConfig setInstance(T instance) {
        this.instance = instance;
        return this;
    }
}
