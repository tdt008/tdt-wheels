package com.tdt.wheel.rpc.consumer;

import com.tdt.wheel.rpc.server.RegistryInfo;

import java.util.List;
import java.util.Random;

/**
 * @author qrc
 * @description TODO
 * @date 2019/8/26
 */
public class RandomLoadbalancer implements LoadBalancer {
    @Override
    public RegistryInfo choose(List<RegistryInfo> registryInfos) {
        Random random = new Random();
        int index = random.nextInt(registryInfos.size());
        return registryInfos.get(index);
    }
}
