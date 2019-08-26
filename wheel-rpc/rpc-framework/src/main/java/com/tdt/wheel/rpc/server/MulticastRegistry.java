package com.tdt.wheel.rpc.server;

import java.util.List;

/**
 * @author qrc
 * @description
 * @date 2019/8/26
 */
public class MulticastRegistry implements Registry {
    public MulticastRegistry(String connectString) {

    }

    @Override
    public void register(Class clazz, RegistryInfo registryInfo) throws Exception {

    }

    @Override
    public List<RegistryInfo> fetchRegistry(Class clazz) throws Exception {
        return null;
    }
}
