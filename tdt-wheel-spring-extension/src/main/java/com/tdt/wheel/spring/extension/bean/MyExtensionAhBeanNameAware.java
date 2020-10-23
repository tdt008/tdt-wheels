package com.tdt.wheel.spring.extension.bean;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * description:
 *  Aware扩展的一种，触发点在bean的初始化之前，也就是postProcessBeforeInitialization之前，这个类的触发点方法只有一个：setBeanName
 *
 *  使用场景为：用户可以扩展这个点，在初始化bean之前拿到spring容器中注册的的beanName，来自行修改这个beanName的值。
 *
 * @date: 2020年10月23日 17:43
 * @author: qinrenchuan
 */
@Component
public class MyExtensionAhBeanNameAware implements BeanNameAware {
    @Override
    public void setBeanName(String name) {
        System.out.println("[MyExtensionAhBeanNameAware#BeanNameAware] "+ name);
    }
}
