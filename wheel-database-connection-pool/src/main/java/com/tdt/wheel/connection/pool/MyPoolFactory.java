package com.tdt.wheel.connection.pool;

/**
 * description: MyPoolFactory
 *
 * @date: 2020年11月14日 16:16
 * @author: qinrenchuan
 */
public class MyPoolFactory {

    public static IMyPool getInstance() {
        return CreatePool.myPool;
    }

    private static class CreatePool {
        public static IMyPool myPool = new MyDefaultPool();
    }

    private MyPoolFactory() {
    }
}
