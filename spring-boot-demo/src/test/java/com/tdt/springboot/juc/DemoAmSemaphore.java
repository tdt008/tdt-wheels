package com.tdt.springboot.juc;

import java.util.concurrent.Semaphore;

/**
 * description: DemoAmSemaphore
 *
 *  Semaphore可以用于做流量控制，特别是公共资源有限的应用场景，比如数据库连接。
 *  假如有多个线程读取数据后，需要将数据保存在数据库中，而可用的最大数据库连接只有10个，
 *  这时候就需要使用Semaphore来控制能够并发访问到数据库连接资源的线程个数最多只有10个。
 *  在限制资源使用的应用场景下，Semaphore是特别合适的。
 *
 * @date: 2020年11月10日 15:52
 * @author: qinrenchuan
 */
public class DemoAmSemaphore {
    public static void main(String[] args) {
        Parking parking = new Parking(3);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    parking.park();
                }
            }).start();
        }
    }


    static class Parking {
        private Semaphore semaphore;

        public Parking(int count) {
            this.semaphore = new Semaphore(count);
        }

        public void park() {
            try {
                semaphore.acquire();
                long time = (long) (Math.random() * 10);
                System.out.println(Thread.currentThread().getName() + "进入停车场，停车" + time + "秒...");
                Thread.sleep(time);
                System.out.println(Thread.currentThread().getName() + "开出停车场...");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }

}
