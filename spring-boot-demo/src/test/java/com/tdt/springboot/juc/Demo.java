package com.tdt.springboot.juc;

/**
 * description: Demo
 *
 * @date: 2020年11月05日 14:35
 * @author: qinrenchuan
 */
public class Demo {
    public static void main(String[] args) {
        Thread.currentThread().getThreadGroup().list();
        System.out.println(Thread.activeCount());
    }
}
