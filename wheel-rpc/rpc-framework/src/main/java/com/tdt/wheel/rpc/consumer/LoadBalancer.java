package com.tdt.wheel.rpc.consumer;

import com.tdt.wheel.rpc.server.RegistryInfo;

import java.util.List;

/**
 * @author qrc
 * @description
 * @date 2019/8/26
 */
public interface LoadBalancer {
    /**
     * 选择一个生产者
     *
     * @param registryInfos 生产者列表
     * @return 选中的生产者
     */
    RegistryInfo choose(List<RegistryInfo> registryInfos);
}
