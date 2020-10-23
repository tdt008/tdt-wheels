package com.tdt.wheel.spring.extension.bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * description:
 *  这个并不算一个扩展点，其实就是一个标注。其作用是在bean的初始化阶段，如果对一个方法标注了@PostConstruct，
 *  会先调用这个方法。这里重点是要关注下这个标准的触发点，这个触发点是在postProcessBeforeInitialization之后，
 *  InitializingBean.afterPropertiesSet之前。
 *
 * @date: 2020年10月23日 18:09
 * @author: qinrenchuan
 */
@Component
public class MyExtensionAiPostConstruct {

    public MyExtensionAiPostConstruct() {
        System.out.println("MyExtensionAiPostConstruct实例化。。。。");
    }

    @PostConstruct
    public void init() {
        System.out.println("[PostConstruct] MyExtensionAiPostConstruct");
    }

    public void hello() {
        System.out.println("MyExtensionAiPostConstruct say hello");
    }
}
