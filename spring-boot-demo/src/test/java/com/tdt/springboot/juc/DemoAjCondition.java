package com.tdt.springboot.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * description:
 *      注意区分 AQS 的同步队列和 Condition 的条件队列。
 *          1.线程抢锁失败时进入 AQS 同步队列，AQS 同步队列中的线程都是等待着随时准备抢锁的。
 *          2.线程因为没有满足某一条件而调用 condition.await()方法之后进入 Condition 条件队列，Condition 条件队列中的线程只能等着，没有获取锁的机会。
 *          3.当条件满足后调用 condition.signal()线程被唤醒，那么线程就从 Condition 条件队列移除，进入 AQS 同步队列，被赋予抢锁继续执行的机会。
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


