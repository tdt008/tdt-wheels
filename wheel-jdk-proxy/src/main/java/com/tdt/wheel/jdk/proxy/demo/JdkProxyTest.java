package com.tdt.wheel.jdk.proxy.demo;

import com.tdt.wheel.custom.myproxy.Man;
import com.tdt.wheel.custom.myproxy.XiaoZhanMan;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

/**
 * description: JdkProxyTest
 *
 * @date: 2020年11月12日 15:29
 * @author: qinrenchuan
 */
public class JdkProxyTest {
    public static void main(String[] args) throws Exception {
        Man man = new XiaoZhanMan();
        ManHandler manHandler = new ManHandler(man);

        Man manProxy = (Man) Proxy.newProxyInstance(
                man.getClass().getClassLoader(),
                new Class[]{Man.class},
                manHandler);
        manProxy.findObject();

        // 打印JVM在内存中生成的动态代理类
        createProxyClassFile(Man.class);
    }

    private static void createProxyClassFile(Class c) {
        byte[] data = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{c});

        // 写入文件
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("$Proxy0.class");
            fileOutputStream.write(data);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
