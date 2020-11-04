package com.tdt.springboot.juc;

/**
 * description: DemoAbThread
 *
 * @date: 2020年11月04日 15:01
 * @author: qinrenchuan
 */
public class DemoAbThread {
    public static void main(String[] args) throws Exception {
        // 10
        new MyThread("高级", Thread.MAX_PRIORITY).start();
        // 5
        new MyThread("普通级", Thread.NORM_PRIORITY).start();
        // 1
        new MyThread("低级", Thread.MIN_PRIORITY).start();
    }

    static class MyThread extends Thread {

        public MyThread(String name, int priority) {
            super(name);
            setPriority(priority);
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                System.out.println(this.getName() + "线程第" + i + "次执行！");
            }
        }
    }

}
