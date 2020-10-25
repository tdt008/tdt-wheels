package com.tdt.wheel.spring.extension.bean;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @description: MyExtensionAoApplicationRunner
 * @version v1.0.0
 * @since 2020/10/25 20:40
 */
@Component
public class MyExtensionAoApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("[MyExtensionAoApplicationRunner#run]...");
    }
}
