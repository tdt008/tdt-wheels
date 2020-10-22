package com.tdt.wheel.spring.extension.bean;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @description: description: 001
 *   spring容器在刷新之前初始化ConfigurableApplicationContext的回调接口，简单来说，
 *   就是在容器刷新之前调用此类的initialize方法。这个点允许被用户自己扩展。
 *   用户可以在整个spring容器还没被初始化之前做一些事情。
 *  可以想到的场景可能为，在最开始激活一些配置，或者利用这时候class还没被类加载器加载的时机，进行动态字节码注入等操作。
 * @author tudoutiao
 * @version v1.0.0
 * @since 2020/10/22 22:30
 */
public class MyExtensionAaApplicationContextInitializer implements ApplicationContextInitializer {

    /**
     * 因为这时候spring容器还没被初始化，所以想要自己的扩展的生效，有以下三种方式：
     * 1) 在启动类中用springApplication.addInitializers(new TestApplicationContextInitializer())语句加入
     *         启动类代码示例：
     *              SpringApplication springApplication = new SpringApplication(Application.class);
     *              springApplication.addInitializers(new MyExtensionAaApplicationContextInitializer());
     *              springApplication.run(args);
     *
     * 2) 配置文件配置application.properties中添加配置
     *           context.initializer.classes=com.tdt.platform.demo.bean.MyExtensionAaApplicationContextInitializer
     *
     *
     * 3) Spring SPI扩展，在META-INF/spring.factories中加入
     *      org.springframework.context.ApplicationContextInitializer=com.tdt.platform.demo.bean.MyExtensionAaApplicationContextInitializer
     */
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("[MyExtensionAaApplicationContextInitializer].....................................................................................................................");
    }
}
