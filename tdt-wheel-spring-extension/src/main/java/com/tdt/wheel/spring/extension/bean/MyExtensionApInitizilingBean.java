package com.tdt.wheel.spring.extension.bean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @description: MyExtensionApInitizilingBean
 * @version v1.0.0
 * @since 2020/10/25 20:42
 */
@Component
public class MyExtensionApInitizilingBean implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("[MyExtensionInitializingBean#afterPropertiesSet]");
    }
}
