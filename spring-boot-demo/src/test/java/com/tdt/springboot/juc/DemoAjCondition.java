package com.tdt.springboot.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * description: DemoAjCondition
 *
 * @date: 2020年11月09日 11:44
 * @author: qinrenchuan
 */
public class DemoAjCondition {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static volatile boolean flag = false;

    public static void main(String[] args) {
        Thread waiterTherd = new Thread(new Waiter());
        waiterTherd.start();

        Thread signlerThread = new Thread(new Signler());
        signlerThread.start();
    }

    private static class Waiter implements Runnable {
        @Override
        public void run() {
            lock.lock();
            try {
                while (!flag) {
                    System.out.println(Thread.currentThread().getName() + "当前条件不满足等待");
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "接收到通知条件满足");
            } finally {
                lock.unlock();
            }
        }
    }

    private static class Signler implements Runnable {
        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "run....睡3秒, 再唤醒其他线程");
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                flag = true;
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }
}


