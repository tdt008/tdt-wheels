package com.tdt.wheel.rpc.consumer;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author qrc
 * @description TODO
 * @date 2019/8/26
 */
public class DefaultInvoker<T> implements Invoker {

    private ChannelHandlerContext ctx;

    private String requestId;

    private String identify;

    private Class returnType;

    private T result;

    public DefaultInvoker(
            Class returnType,
            ChannelHandlerContext ctx,
            String requestId,
            String identify
            ) {
        this.ctx = ctx;
        this.requestId = requestId;
        this.identify = identify;
        this.returnType = returnType;
    }

    @Override
    public Object invoke(Object[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("interfaces", identify);

        JSONObject param = new JSONObject();
        if (args != null) {
            for (Object obj : args) {
                param.put(obj.getClass().getName(), obj);
            }
        }
        jsonObject.put("parameter", param);
        jsonObject.put("requestId", requestId);
        System.out.println("发送给服务端JSON为：" + jsonObject.toJSONString());
        String msg = jsonObject.toJSONString() + "$$";
        ByteBuf byteBuf = Unpooled.buffer(msg.getBytes().length);
        byteBuf.writeBytes(msg.getBytes());
        ctx.writeAndFlush(byteBuf);
        waitForResult();

        return result;
    }

    @Override
    public void setResult(String result) {
        synchronized (this) {
            this.result = (T) JSONObject.parseObject(result, returnType);
            notifyAll();
        }
    }

    private void waitForResult() {
        synchronized (this) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
