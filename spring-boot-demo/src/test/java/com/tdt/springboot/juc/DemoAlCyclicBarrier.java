package com.tdt.springboot.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * description: DemoAlCyclicBarrier
 *
 *  CyclicBarrier与CountDownLatch的比较：
 *      1. CountDownLatch用于一个线程等待若干个其他线程执行完任务之后才执行，强调一个线程等待，这个线程会阻塞。
 *          而CyclicBarrier用于一组线程互相等待至某个状态，然后这一组线程再同时执行，强调的是多个线程互等，这多个线程阻塞，等大家都完成，再携手共进。
 *      2. CountDownLatch是不能复用的，而CyclicLatch是可以复用的。使用reset()方法将屏障重置为初始状态之后就可以复用。
 *      3. CyclicBarrier提供了更多的方法，能够通过getNumberWaiting()获取阻塞线程的数量，通过isBroken()方法可以知道阻塞的线程是否被中断。
 *
 * @date: 2020年11月10日 15:39
 * @author: qinrenchuan
 */
public class DemoAlCyclicBarrier {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(8, () -> {
            System.out.println("所有运动员入场，裁判员一声令下！！！");
        });

        System.out.println("运动员准备进场，全场欢呼......");
        for (int i = 0; i < 8; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " 运动员到达起点，准备好了！！！");
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + " 运动员出发！！！");
                }
            }).start();
        }
    }
}
