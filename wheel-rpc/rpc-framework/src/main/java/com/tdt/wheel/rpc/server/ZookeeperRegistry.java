package com.tdt.wheel.rpc.server;/**
 * Created by rc on 2019/8/25.
 */

import com.alibaba.fastjson.JSONArray;
import com.tdt.wheel.rpc.consumer.ReferenceConfig;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qrc
 * @description ZookeeperRegistry
 * @date 2019/8/25
 */
public class ZookeeperRegistry implements Registry {

    private CuratorFramework client;

    public ZookeeperRegistry(String connectString) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.newClient(connectString, retryPolicy);
        client.start();

        try {
            Stat myRPC = client.checkExists().forPath("/myRPC");
            if (myRPC == null) {
                client.create()
                        .creatingParentsIfNeeded()
                        .forPath("/myRPC");
            }
            System.out.println("Zookeeper Client初始化完毕......");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void register(Class clazz, RegistryInfo registryInfo) throws Exception {
        // 1. 注册的时候，先从zk中获取数据
        // 2. 将自己的服务器地址加入注册中心中

        // 为每一个接口的每一个方法注册一个临时节点，然后key为接口方法的唯一标识，data为服务地址列表

        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            String key = InvokeUtils.buildInterfaceMethodIdentify(clazz, method);
            String path = "/myRPC" + key;
            Stat stat = client.checkExists().forPath(path);
            List<RegistryInfo> registryInfos;
            if (stat != null) {
                // 如果这个接口已经有人注册过了，把数据拿回来，然后将自己的信息保存进去
                byte[] bytes = client.getData().forPath(path);
                String data = new String(bytes, StandardCharsets.UTF_8);
                registryInfos = JSONArray.parseArray(data, RegistryInfo.class);

                if (registryInfos.contains(registryInfo)) {
                    // 正常来说，zk的临时节点，断开连接后，直接就没了，但是重启会经常发现存在节点，所以有了这样的代码
                    System.out.println("地址列表已经包含本机【" + key + "】，不注册了");
                } else {
                    registryInfos.add(registryInfo);
                    client.setData().forPath(path, JSONArray.toJSONString(registryInfos).getBytes());

                    System.out.println("注册到注册中心，路径为：【" + path + "】 信息为：" + registryInfo);
                }
            } else {
                registryInfos = new ArrayList<>();
                registryInfos.add(registryInfo);
                client.create()
                        .creatingParentsIfNeeded()
                        // 临时节点，断开连接就关闭
                        .withMode(CreateMode.EPHEMERAL)
                        .forPath(path, JSONArray.toJSONString(registryInfos).getBytes());

                System.out.println("注册到注册中心，路径为：【" + path + "】 信息为：" + registryInfo);
            }
        }
    }


    /**
     * @description 为服务提供者抓取注册表
     * @param clazz
     * @return java.util.List
     * @author qrc
     * @date 2019/8/26
     */
    @Override
    public List<RegistryInfo> fetchRegistry(Class clazz) throws Exception {
        Method[] declaredMethods = clazz.getDeclaredMethods();
        List<RegistryInfo> registryInfos = null;
        for (Method method : declaredMethods) {
            String key = InvokeUtils.buildInterfaceMethodIdentify(clazz, method);
            String path = "/myRPC/" + key;
            Stat stat = client.checkExists()
                    .forPath(path);
            if (stat == null) {
                // 这里可以添加watcher来监听变化，这里简化了，没有做这个事情
                System.out.println("警告：无法找到服务接口：" + path);
                continue;
            }
            if (registryInfos == null) {
                byte[] bytes = client.getData().forPath(path);
                String data = new String(bytes, StandardCharsets.UTF_8);
                registryInfos = JSONArray.parseArray(data, RegistryInfo.class);
            }
        }
        return registryInfos;
    }
}
