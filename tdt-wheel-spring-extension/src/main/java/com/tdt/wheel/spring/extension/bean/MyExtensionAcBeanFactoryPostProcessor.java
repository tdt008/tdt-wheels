package com.tdt.wheel.spring.extension.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * description:
 *      是beanFactory的扩展接口，调用时机在spring在读取beanDefinition信息之后，实例化bean之前
 *      在这个时机，用户可以通过实现这个扩展接口来自行处理一些东西，比如修改已经注册的beanDefinition的元信息
 *
 * @date: 2020年10月23日 10:23
 * @author: qinrenchuan
 */
@Component
public class MyExtensionAcBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("[MyExtensionAcBeanFactoryPostProcessor#postProcessBeanFactory].............");
    }
}
