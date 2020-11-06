package com.tdt.springboot.juc;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * description: DemoAhReadWriteLock
 *
 * @date: 2020年11月06日 14:39
 * @author: qinrenchuan
 */
public class DemoAhReadWriteLock {
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
