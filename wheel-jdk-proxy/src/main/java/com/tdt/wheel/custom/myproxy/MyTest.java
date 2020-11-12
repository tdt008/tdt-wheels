package com.tdt.wheel.custom.myproxy;

/**
 * description: MyTest
 *
 * @date: 2020年11月12日 18:43
 * @author: qinrenchuan
 */
public class MyTest {
    public static void main(String[] args) throws Exception {
        Man man = new XiaoZhanMan();
        MyHandler myHandler = new MyHandler(man);

        Man proxyMan = (Man) MyProxy.newProxyInstance(
                new MyClassLoader("F:\\tdt-wheels\\tdt-wheels\\wheel-jdk-proxy\\src\\main\\java\\com\\tdt\\wheel\\custom\\myproxy", "myproxy"),
                Man.class, myHandler);
        System.out.println(proxyMan.getClass().getName());
        proxyMan.findObject();
    }
}
