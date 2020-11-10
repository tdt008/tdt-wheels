package com.tdt.springboot.juc;

import java.util.concurrent.Exchanger;

/**
 * description: DemoAnExchanger
 *
 *      Exchanger是一个用于线程间协作的工具类，用于两个线程间交换
 *      Exchanger提供了一个交换的同步点，在这个同步点两个线程能够交换数据。
 *          具体交换数据是通过exchange()方法来实现的，如果一个线程先执行exchange方法，
 *          那么它会同步等待另一个线程也执行exchange方法，这个时候两个线程就都达到了同步点，两个线程就可以交换数据。
 *
 * @date: 2020年11月10日 16:07
 * @author: qinrenchuan
 */
public class DemoAnExchanger {
    private static Exchanger<String> exchanger = new Exchanger<>();

    static String goods = "电脑";
    static String money = "$1000";

    public static void main(String[] args) {
        System.out.println("准备交易，一手交钱一手交货...");

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " 卖家到了，已经准备好货：" + goods);
                try {
                    String exchange = exchanger.exchange(goods);
                    System.out.println(Thread.currentThread().getName() + " 卖家收到了钱" + exchange);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " 买家到了，已经准备好钱：" + money);
                try {
                    String exchange = exchanger.exchange(money);
                    System.out.println(Thread.currentThread().getName() + " 卖家收到了货物" + exchange);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
