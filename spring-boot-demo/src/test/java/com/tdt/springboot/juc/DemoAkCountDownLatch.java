package com.tdt.springboot.juc;

import java.util.concurrent.CountDownLatch;

/**
 * description: DemoAkCountDownLatch
 *
 * @date: 2020年11月10日 14:56
 * @author: qinrenchuan
 */
public class DemoAkCountDownLatch {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        Thread bossThread = new Thread(new Boss(countDownLatch));
        bossThread.start();

        for (int i = 0; i < 5; i++) {
            new Thread(new Employee(countDownLatch)).start();
        }
    }

    static class Boss implements Runnable {
        private CountDownLatch countDownLatch;

        public Boss(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " : boss 已经进入会议室，等员工到齐再开会");
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " : 员工已经到齐，现在开始开会。");
        }
    }

    static class Employee implements Runnable {
        private CountDownLatch countDownLatch;

        public Employee(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println("员工: " + Thread.currentThread().getName() + " 到达会议室!");
            countDownLatch.countDown();
        }
    }
}
