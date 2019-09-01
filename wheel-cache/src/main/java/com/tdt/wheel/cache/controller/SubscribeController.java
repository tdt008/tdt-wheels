package com.tdt.wheel.cache.controller;/**
 * Created by rc on 2019/9/1.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author qrc
 * @description reddis发布订阅异步回调
 * @date 2019/9/1
 */
@RestController
@RequestMapping("/subscribe")
public class SubscribeController {

    /**
     * redis容器对象
     */
    @Autowired
    private RedisMessageListenerContainer redisMessageListenerContainer;
    /**
     * 引入Redis客户端操作对象
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 刚方法为入口方法：
     *  1、首先调用该方法
     *  2、创建监听
     *  3、同步阻塞等待回调（触发发布消息的方法为triggerCall）
     * @param callName
     * @param requestId
     * @return java.lang.String
     * @author qrc
     * @date 2019/9/1
     */
    @GetMapping("/call/{requestId}")
    public String subscribeCall(@RequestParam("callName") String callName, @PathVariable("requestId") Long requestId) {
        // 此处实现异步消息调用处理....

        // 生成监听key
        String key = "CALL_" + callName + "_" + requestId;
        // 创建监听topic
        ChannelTopic channelTopic = new ChannelTopic(key);
        // 创建消息任务对象
        CallableMessageTask callableMessageTask = new CallableMessageTask();

        redisMessageListenerContainer.addMessageListener(new CallMessageListener(callableMessageTask), channelTopic);
        System.out.println("start redis subscribe listener->" + key);
        // 进入同步阻塞等待，超时时间设置为60秒
        try {
            Message message = (Message) callableMessageTask.getCallableMessageFuture().get(60000, TimeUnit.MILLISECONDS);
            System.out.println("callback message->" + message.toString());
            return message.toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            // 销毁消息监听对象
            if (callableMessageTask != null) {
                redisMessageListenerContainer.removeMessageListener(callableMessageTask.getMessageListener());
            }
        }
        return "FAIL";
    }


    /**
     * 该方法向redis订阅主题发送消息，然后订阅者收到消息后出发异步回调
     * @param callName
     * @param requestId
     * @return boolean
     * @author qrc
     * @date 2019/9/1
     */
    @GetMapping("/triggerCall/{requestId}")
    public boolean triggerCall(@RequestParam("callName") String callName, @PathVariable("requestId") Long requestId) {
        //生成监听频道Key
        String key = "CALL_" + callName + "_" + requestId;
        //模拟实现消息回调
        stringRedisTemplate.convertAndSend(key, "我就是我不一样的烟火");
        return true;
    }


    /**
     * 消息任务对象
     * @author qrc
     * @date 2019/9/1
     */
    public static class CallableMessageTask<T> {
        /**
         * 声明线程异步阻塞对象
         */
        private CompletableFuture<T> callableMessageFuture = new CompletableFuture<T>();

        /**
         * 声明消息监听对象
         */
        private MessageListener messageListener;

        /**
         * 是否超时
         */
        private boolean isTimeout;

        public CompletableFuture<T> getCallableMessageFuture() {
            return callableMessageFuture;
        }

        public CallableMessageTask setCallableMessageFuture(CompletableFuture<T> callableMessageFuture) {
            this.callableMessageFuture = callableMessageFuture;
            return this;
        }

        public MessageListener getMessageListener() {
            return messageListener;
        }

        public CallableMessageTask setMessageListener(MessageListener messageListener) {
            this.messageListener = messageListener;
            return this;
        }

        public boolean isTimeout() {
            return isTimeout;
        }

        public CallableMessageTask setTimeout(boolean timeout) {
            isTimeout = timeout;
            return this;
        }
    }

    /**
     * 任务监听对象
     * @author qrc
     * @date 2019/9/1
     */
    public static class CallMessageListener implements MessageListener {

        private  CallableMessageTask callableMessageTask;

        public CallMessageListener(CallableMessageTask callableMessageTask) {
            this.callableMessageTask = callableMessageTask;
            this.callableMessageTask.setMessageListener(this);
        }

        /**
         * 实现消息发布监听方法
         * @param message
         * @param bytes
         * @return void
         * @author qrc
         * @date 2019/9/1
         */
        @Override
        public void onMessage(Message message, @Nullable byte[] bytes) {
            System.out.println("onMessage response:{}" + message.toString());
            // 线程阻塞完成
            callableMessageTask.getCallableMessageFuture().complete(message);
        }
    }
}
