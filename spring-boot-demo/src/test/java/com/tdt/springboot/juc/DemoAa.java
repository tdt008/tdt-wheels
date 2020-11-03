package com.tdt.springboot.juc;

/**
 * description:
 *  硬件的发展中，一直存在一个矛盾，CPU、内存、I/O设备的速度差异。
 *          速度排序：CPU >> 内存 >> I/O设备
 *  为了平衡这三者的速度差异，做了如下优化：
 *  1、CPU 增加了缓存，以均衡内存与CPU的速度差异；
 *  2、操作系统增加了进程、线程，以分时复用CPU，进而均衡I/O设备与CPU的速度差异；
 *  3、编译程序优化指令执行次序，使得缓存能够得到更加合理地利用。
 *
 *
 *  在执行程序时为了提高性能，编译器和处理器常常会对指令做重排序。
 *  从Java源代码到最终实际执行，要经历三种重排序：编译器优化的重排序、指令级并行的重排序、内存系统的重排序。
 *  as-if-serial语义要求：不管怎么重排序，程序的执行结果不能被改变。
 *  存在数据依赖关系的两个操作，不可以重排序。
 *  重排序可能会导致多线程程序出现可见性问题和有序性问题。
 *  JMM编译时在当位置会插入内存屏障指令来禁止特定类型的重排序。
 *
 *
 *   happens-before
 *
 * 一个操作执行的结果需要对另一个操作可见，那么这两个操作之间必须存在happens-before关系。
 * 两个操作可以是单线程或多线程，happens-before解决的就是多线程内存可见性问题。区分数据依赖性和as-if-seial针对单线程。
 * happens-before原则定义如下：
 *  1）一个操作happens-before另一个操作，那么第一个操作的执行结果将对第二个操作可见，而且第一个操作的执行顺序排在第二个操作之前。
 *  2）两个操作之间存在happens-before关系，并不意味着一定要按照happens-before原则制定的顺序来执行。如果重排序之后的执行结果与按照happens-before关系来执行的结果一致，那么这种重排序并不非法。
 *
 * happens-before原则规则：
 *  1）程序次序规则：一个线程内，按照代码顺序，书写在前面的操作先行发生于书写在后面的操作；
 *  2）锁定规则：一个unLock操作先行发生于后面对同一个锁额lock操作；
 *  3）volatile变量规则：对一个变量的写操作先行发生于后面对这个变量的读操作；
 *  4）传递规则：如果操作A先行发生于操作B，而操作B又先行发生于操作C，则可以得出操作A先行发生于操作C；
 *  5）线程启动规则：Thread对象的start()方法先行发生于此线程的每个一个动作；
 *  6）线程中断规则：对线程interrupt()方法的调用先行发生于被中断线程的代码检测到中断事件的发生；
 *  7）线程终结规则：线程中所有的操作都先行发生于线程的终止检测，我们可以通过	Thread.join()方法结束、Thread.isAlive()的返回值手段检测到线程已经终止执行；
 *  8）对象终结规则：一个对象的初始化完成先行发生于他的finalize()方法的开始；
 *
 *  volatile的禁止重排序规则：
 * 1）当第二个操作是volatile写时，不管第一个操作是什么，都不能重排序。这个规则确保volatile写之前的操作不会被编译器重排序到volatile写之后。
 * 2）当第一个操作是volatile读时，不管第二个操作是什么，都不能重排序。这个规则确保volatile读之后的操作不会被编译器重排序到volatile读之前。
 * 3）当第一个操作是volatile写，第二个操作是volatile读时，不能重排序。
 *
 *
 * 对于final域，编译器和处理器要遵守两个重排序规则：
 *
 *      在构造函数内对一个final域的写入，与随后把这个被构造对象的引用赋值给一个引用变量，这两个操作之间不能重排序。（先写入final变量，后调用该对象引用）
 * 原因：编译器会在final域的写之后，插入一个StoreStore屏障
 *
 *      初次读一个包含final域的对象的引用，与随后初次读这个final域，这两个操作之间不能重排序（先读对象的引用，后读final变量）
 * 原因：编译器会在读final域操作的前面插入一个LoadLoad屏障
 *
 * @date: 2020年11月03日 16:31
 * @author: qinrenchuan
 */
public class DemoAa {
    public int a = 0;

    public void increase() {
        a++;
    }

    public static void main(String[] args) {
        final DemoAa test = new DemoAa();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 1000; j++)
                        test.increase();
                };
            }.start();
        }

        try {
            Thread.sleep(10000L);
            System.out.println(test.a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
