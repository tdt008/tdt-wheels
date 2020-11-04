package com.tdt.springboot.juc;

/**
 * description:
 *      调用 setDaemon(boolean on)设置守护线程要在线程启动前，否则会抛出异常。
 *      守护线程在退出的时候并不会执行 finnaly 块中的代码，所以将释放资源等操作不要放在 finnaly 块中执行，这种操作是不安全的。
 *
 * @date: 2020年11月04日 15:14
 * @author: qinrenchuan
 */
public class DemoAcDaemon {
    public static void main(String[] args) {
        Thread t1 = new MyCommon();
        Thread t2 = new Thread(new MyDaemon());
        t2.setDaemon(true); // 设置为守护线程

        t2.start();
        t1.start();
    }
}

class MyCommon extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("线程1第" + i + "次执行！");
            try {
                Thread.sleep(7);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyDaemon implements Runnable {
    public void run() {
        for (long i = 0; i < 9999999L; i++) {
            System.out.println("后台线程第" + i + "次执行！");
            try {
                Thread.sleep(7);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
