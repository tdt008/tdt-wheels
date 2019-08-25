package com.tdt.wheel.rpc.server;/**
 * Created by rc on 2019/8/25.
 */



/**
 * @author qrc
 * @description
 * @date 2019/8/25
 */
public interface Registry {
    /**
     * 将生产者接口注册到注册中心
     *
     * @param clazz        类
     * @param registryInfo 本机的注册信息
     */
    void register(Class clazz, RegistryInfo registryInfo) throws Exception;
}
