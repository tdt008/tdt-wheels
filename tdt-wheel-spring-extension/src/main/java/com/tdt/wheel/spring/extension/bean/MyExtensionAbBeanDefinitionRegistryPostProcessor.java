package com.tdt.wheel.spring.extension.bean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * @description:
 *       这个接口在读取项目中的beanDefinition之后执行，提供一个补充的扩展点
 *       使用场景：你可以在这里动态注册自己的beanDefinition，可以加载classpath之外的bean
 * @author tudoutiao
 * @version v1.0.0
 * @since 2020/10/22 22:31
 */
@Component
public class MyExtensionAbBeanDefinitionRegistryPostProcessor implements
        BeanDefinitionRegistryPostProcessor {

    /**
     * 动态将Bean注册
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("[MyExtensionAbBeanDefinitionRegistryPostProcessor#postProcessBeanDefinitionRegistry].............");

        // 创建一个bean的定义类的对象
        BeanDefinition beanDefinition = new RootBeanDefinition(MyExtensionAba.class);
        // 将Bean 的定义注册到Spring环境
        // registry.registerBeanDefinition("myExtensionAb", beanDefinition);
        registry.registerBeanDefinition(MyExtensionAba.class.getName(), beanDefinition);
    }

    public static class MyExtensionAba {
        public String hello() {
            return "MyExtensionAbBeanDefinitionRegistryPostProcessor.MyExtensionAb#hello";
        }
    }

    /**
     *
     * 可以修改各个注册的Bean
     *
     * 在实际使用过程中，Spring启动时扫描自定义注解，是通过BeanFactoryPostProcessor接口的postProcessBeanFactory方法，
     * configurableListableBeanFactory.getBeansWithAnnotation(AutoDiscoverClass.class);获取每一个有自定义注解的Bean
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("[MyExtensionAbBeanDefinitionRegistryPostProcessor#postProcessBeanFactory].............");

        // bean的名字为key, bean的实例为value
        Map<String, Object> beansWithAnnotationMap = beanFactory.getBeansWithAnnotation(MyCompont.class);
        if (!CollectionUtils.isEmpty(beansWithAnnotationMap)) {
            for (Map.Entry<String, Object> entry : beansWithAnnotationMap.entrySet()) {
                System.out.println(entry.getKey() + " <:> " + entry.getValue().getClass());
            }
        }
    }


    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public static @interface MyCompont {

    }

    @MyCompont
    @Component
    public static class MyExtensionAbb {
        public String hello() {
            return "MyExtensionAbBeanDefinitionRegistryPostProcessor.MyExtensionAb#hello";
        }
    }
}

