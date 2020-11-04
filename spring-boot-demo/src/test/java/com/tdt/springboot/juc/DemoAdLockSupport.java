package com.tdt.springboot.juc;

import java.util.concurrent.locks.LockSupport;

/**
 * description: DemoAdLockSupport
 *
 *  每个线程都会与一个许可关联，这个许可对应一个 Parker 的实例，Parker 有一个 int 类型的属性_count。
 *      park()方法：
 *          将_count 变为 0
 *          如果原_count==0，将线程阻塞
 *
 *      unpark()方法：
 *          将_count 变为 1
 *          如果原_count==0，将线程唤醒
 *
 *
 *
 *        1）Object 的 wait()/notify 方法需要获取到对象锁之后在同步代码块里才能调用，而 LockSupport 不需要获取锁。
 *          所以使用 LockSupport 线程间不需要维护一个共享的同步对象，从而实现了线程间的解耦。
 *        2） unark()方法可提前 park()方法调用，所以不需要担心线程间执行的先后顺序。
 *        3） 多次调用 unpark()方法和调用一次 unpark()方法效果一样，因为 unpark 方法是直接将_counter 赋值为 1，
 *          而不是加 1。
 *        4） 许可不可重入，也就是说只能调用一次 park()方法，如果多次调用 park()线程会一直阻塞
 *
 * @date: 2020年11月04日 19:58
 * @author: qinrenchuan
 */
public class DemoAdLockSupport {
    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " run....");
                LockSupport.park();
                System.out.println(Thread.currentThread().getName() + " weak-run....");
            }
        };
        thread.start();

        try {
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " sleep over.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        LockSupport.unpark(thread);
    }
}
