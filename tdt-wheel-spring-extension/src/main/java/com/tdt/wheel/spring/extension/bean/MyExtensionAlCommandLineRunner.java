package com.tdt.wheel.spring.extension.bean;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * description:
 *      这个接口也只有一个方法：run(String… args)，触发时机为整个项目启动完毕后，自动执行。
 *      如果有多个CommandLineRunner，可以利用@Order来进行排序。
 *
 *      使用场景：用户扩展此接口，进行启动项目之后一些业务的预处理。
 *
 * @date: 2020年10月23日 20:06
 * @author: qinrenchuan
 */
@Component
public class MyExtensionAlCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("[MyExtensionAlCommandLineRunner#run]");
    }
}
