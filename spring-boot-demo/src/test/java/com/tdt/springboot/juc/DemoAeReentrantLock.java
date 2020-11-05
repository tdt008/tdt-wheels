package com.tdt.springboot.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * description: DemoAeReentrantLock
 *
 * @date: 2020年11月05日 14:19
 * @author: qinrenchuan
 */
public class DemoAeReentrantLock {

    private ReentrantLock lock  = new ReentrantLock();

    private int inc = 0;

    public void increase() {
        lock.lock();
        try {
            inc++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        DemoAeReentrantLock demo = new DemoAeReentrantLock();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        demo.increase();
                    }
                }
            }).start();
        }

        while (Thread.activeCount() > 1) {
            System.out.println(Thread.activeCount());
            // 保证前面的线程都执行完
            Thread.yield();
        }

        System.out.println(demo.inc);
    }




}
