package com.tdt.wheel.rpc.consumer;

/**
 * @author qrc
 * @description TODO
 * @date 2019/8/26
 */
public class ReferenceConfig {
    private Class type;

    public ReferenceConfig(Class type) {
        this.type = type;
    }

    public Class getType() {
        return type;
    }

    public ReferenceConfig setType(Class type) {
        this.type = type;
        return this;
    }
}
