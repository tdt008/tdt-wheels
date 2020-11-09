package com.tdt.springboot.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.StampedLock;

/**
 * description: StampedLock类，在JDK1.8时引入，是对读写锁ReentrantReadWriteLock的增强，
 *              该类提供了一些功能，优化了读锁、写锁的访问，同时使读写锁之间可以互相转换，
 *              更细粒度控制并发。在JDK1.8时引入，是对读写锁ReentrantReadWriteLock的增强，
 *              该类提供了一些功能，优化了读锁、写锁的访问，同时使读写锁之间可以互相转换，更细粒度控制并发。
 *
 *             为什么有了ReentrantReadWriteLock，还要引入StampedLock？
 *             ReentrantReadWriteLock使得多个读线程同时持有读锁（只要写锁未被占用），而写锁是独占的。
 *             但是，读写锁如果使用不当，很容易产生“饥饿”问题：
 *                  比如在读线程非常多，写线程很少的情况下，很容易导致写线程“饥饿”，
 *                  虽然使用“公平”策略可以一定程度上缓解这个问题，
 *                  但是“公平”策略是以牺牲系统吞吐量为代价的。
 *
 * @date: 2020年11月09日 11:14
 * @author: qinrenchuan
 */
public class DemoAiStampedLock {

    public static void main(String[] args) {
        StampedLock stampedLock = new StampedLock();
        Lock readLock = stampedLock.asReadLock();


        Lock writeLock = stampedLock.asWriteLock();


        ReadWriteLock readWriteLock = stampedLock.asReadWriteLock();

    }
}

class Point {
    private double x, y;
    private final StampedLock sl = new StampedLock();

    void move(double deltaX, double deltaY) {
        // 涉及对共享变量的操作，使用写锁-独占操作
        long stamp = sl.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            sl.unlockWrite(stamp);
        }
    }

    /**
     * 乐观读锁tryOptimisticRead
     * 计算当前位置到原点的距离
     */
    double distanceFromOrigin() {
        long stamp = sl.tryOptimisticRead();    // 尝试获取乐观读锁(1)
        double currentX = x, currentY = y;      // 将全部变量拷贝到方法体栈内(2)

        // 检查票据是否可用，即写锁有没有被占用(3)
        if (!sl.validate(stamp)) {
            // 如果写锁被抢占，即数据进行了写操作，则重新获取
            stamp = sl.readLock();// 获取悲观读锁(4)
            try {
                // 将全部变量拷贝到方法体栈内(5)
                currentX = x;
                currentY = y;
            } finally {
                sl.unlockRead(stamp);// 释放悲观读锁(6)
            }
        }

        return Math.sqrt(currentX * currentX + currentY * currentY);// 真正读取操作，返回计算结果(7)
    }

    /**
     * 悲观读锁readLock
     * 如果当前坐标为原点则移动到指定的位置
     */
    void moveIfAtOrigin(double newX, double newY) {
        long stamp = sl.readLock();// 获取悲观读锁(1)
        try {
            // 如果当前点在原点则移动(2)
            while (x == 0.0 && y == 0.0) {
                long ws = sl.tryConvertToWriteLock(stamp);// 尝试将获取的读锁升级为写锁(3)

                if (ws != 0L) {
                    // 升级成功，则更新票据，并设置坐标值，然后退出循环(4)
                    stamp = ws;
                    x = newX;
                    y = newY;
                    break;
                } else {
                    // 读锁升级写锁失败，则释放读锁，显示获取独占写锁，然后循环重试(5)
                    sl.unlockRead(stamp);
                    stamp = sl.writeLock();
                }
            }
        } finally {
            sl.unlock(stamp);// 释放写锁(6)
        }
    }

}
