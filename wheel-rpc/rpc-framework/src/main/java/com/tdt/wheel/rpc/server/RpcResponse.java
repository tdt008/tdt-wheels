package com.tdt.wheel.rpc.server;

/**
 * @author qrc
 * @description RpcResponse
 * @date 2019/8/26
 */
public class RpcResponse {

    private String result;

    private String interfaceMethodIdentify;

    private String requestId;

    private RpcResponse() {
    }

    public static RpcResponse create(String result, String interfaceIdentity, String requestId) {
        RpcResponse response = new RpcResponse();
        return response.setResult(result)
                .setInterfaceMethodIdentify(interfaceIdentity)
                .setRequestId(requestId);
    }

    public String getResult() {
        return result;
    }

    public RpcResponse setResult(String result) {
        this.result = result;
        return this;
    }

    public String getInterfaceMethodIdentify() {
        return interfaceMethodIdentify;
    }

    public RpcResponse setInterfaceMethodIdentify(String interfaceMethodIdentify) {
        this.interfaceMethodIdentify = interfaceMethodIdentify;
        return this;
    }

    public String getRequestId() {
        return requestId;
    }

    public RpcResponse setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }
}
