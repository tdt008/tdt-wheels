package com.tdt.wheel.spring.extension.controller;

import com.tdt.wheel.spring.extension.bean.MyExtensionAbBeanDefinitionRegistryPostProcessor;
import com.tdt.wheel.spring.extension.bean.MyExtensionAiPostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: TestController
 * @author tudoutiao
 * @version v1.0.0
 * @since 2020/10/22 22:29
 */
@RestController
public class TestController {
    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    private MyExtensionAiPostConstruct myExtensionAiPostConstruct;

    @GetMapping("/hello")
    public String hello() {
        return appName + "[" + System.currentTimeMillis() + "]";
    }

    @Autowired
    private MyExtensionAbBeanDefinitionRegistryPostProcessor.MyExtensionAba myExtensionAba;

    @GetMapping("/hello2")
    public String hello2() {
        return myExtensionAba.hello();
    }

    @GetMapping("/hello3")
    public String hello3() {
        myExtensionAiPostConstruct.hello();
        return myExtensionAba.hello();
    }

}
