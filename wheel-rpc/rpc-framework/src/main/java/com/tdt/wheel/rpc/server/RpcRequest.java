package com.tdt.wheel.rpc.server;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author qrc
 * @description RpcRequest
 * @date 2019/8/26
 */
public class RpcRequest {

    private String interfaceIdentity;

    private Map<String, Object> parameterMap = new HashMap<>();

    private ChannelHandlerContext ctx;

    private String requestId;


    public static RpcRequest parse(String message, ChannelHandlerContext ctx) throws ClassNotFoundException {
        /*
         * {
         *   "interfaces":"interface=com.study.rpc.test.producer.HelloService&method=sayHello2¶meter=java.lang
         * .String,com.study.rpc.test.producer.TestBean",
         *   "parameter":{
         *      "java.lang.String":"haha",
         *      "com.study.rpc.test.producer.TestBean":{
         *              "name":"小王",
         *              "age":20
         *        }
         *    }
         * }
         */
        JSONObject jsonObject = JSONObject.parseObject(message);
        String interfaces = jsonObject.getString("interfaces");

        JSONObject parameter = jsonObject.getJSONObject("parameter");
        Set<String> keys = parameter.keySet();
        RpcRequest request = new RpcRequest();
        request.setInterfaceIdentity(interfaces);

        Map<String, Object> parameterMap = new HashMap<>(16);

        String requestId = jsonObject.getString("requestId");

        for (String key : keys) {
            if (key.equals("java.lang.String")) {
                parameterMap.put(key, parameter.getString(key));
            } else {
                Class clazz = Class.forName(key);
                Object object = parameter.getObject(key, clazz);
                parameterMap.put(key, object);
            }
        }

        return request.setParameterMap(parameterMap)
                .setCtx(ctx)
                .setRequestId(requestId);
    }

    public String getInterfaceIdentity() {
        return interfaceIdentity;
    }

    public RpcRequest setInterfaceIdentity(String interfaceIdentity) {
        this.interfaceIdentity = interfaceIdentity;
        return this;
    }

    public Map<String, Object> getParameterMap() {
        return parameterMap;
    }

    public RpcRequest setParameterMap(Map<String, Object> parameterMap) {
        this.parameterMap = parameterMap;
        return this;
    }

    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    public RpcRequest setCtx(ChannelHandlerContext ctx) {
        this.ctx = ctx;
        return this;
    }

    public String getRequestId() {
        return requestId;
    }

    public RpcRequest setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }
}
