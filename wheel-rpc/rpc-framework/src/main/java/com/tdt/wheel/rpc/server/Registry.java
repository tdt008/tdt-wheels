package com.tdt.wheel.rpc.server;/**
 * Created by rc on 2019/8/25.
 */


import com.tdt.wheel.rpc.consumer.ReferenceConfig;

import java.util.List;

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
    
    /**
     * @description 为服务提供者抓取注册表
     * @param clazz
     * @return java.util.List
     * @author qrc
     * @date 2019/8/26
     */
    List<RegistryInfo> fetchRegistry(Class clazz) throws Exception;
}
