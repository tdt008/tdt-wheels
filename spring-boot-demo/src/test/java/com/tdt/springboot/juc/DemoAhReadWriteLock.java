package com.tdt.springboot.juc;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * description: DemoAhReadWriteLock
 *
 * @date: 2020年11月06日 14:39
 * @author: qinrenchuan
 */
public class DemoAhReadWriteLock {

    public static void main(String[] args) {
        // ReentrantReadWriteLock state高16位表示读锁，低16位表示写锁

        // 二进制 00010000
        int SHARED_SHIFT   = 16;
        // 10000000000000000 = 65536
        int SHARED_UNIT    = (1 << SHARED_SHIFT);
        // 10000000000000000 - 1 = 65536 - 1 = 65535
        int MAX_COUNT      = (1 << SHARED_SHIFT) - 1;
        // 10000000000000000 - 1 = 65536 - 1 = 65535
        int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;

    }

    /**
     * 获取读锁的状态，读锁的获取次数(包括重入)
     * c无符号补0右移16位，获得高16位
     */
    static int sharedCount(int c)    {
        //return c >>> SHARED_SHIFT;
        return  c >>> 16;
    }

    /**
     * 获取写锁的状态，写锁的重入次数
     * c & 0x0000FFFF，将高16位全部抹去，获得低16位
     */
    static int exclusiveCount(int c) {
        // return c & EXCLUSIVE_MASK;
        int EXCLUSIVE_MASK = (1 << 16) - 1;
        // 两个同时为1，结果为1，否则为0
        // c & (0000 0000 0000 0000 1111 1111 1111 1111)
        return c & EXCLUSIVE_MASK;
    }


    Object data;
    volatile boolean cacheValid;

    final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    void processCachedData() {
        readWriteLock.readLock().lock();

        if (!cacheValid) {
            readWriteLock.readLock().unlock();
            readWriteLock.writeLock().lock();

            try {
                if (!cacheValid) {
                    data = 1;

                    cacheValid = true;
                }
                readWriteLock.readLock().lock();
            } finally {
                readWriteLock.writeLock().unlock();
            }
        }
        try {
            // user(data)
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
