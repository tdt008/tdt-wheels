package com.tdt.wheel.spring.extension.bean;

import org.springframework.context.*;

/**
 * description: org.springframework.context.support.ApplicationContextAwareProcessor
 *
 *   该类本身并没有扩展点，但是该类内部却有6个扩展点可供实现 ，这些类触发的时机在bean实例化之后，初始化之前
 *
 *   private void invokeAwareInterfaces(Object bean) {
 * 		if (bean instanceof EnvironmentAware) {
 * 			((EnvironmentAware) bean).setEnvironment(this.applicationContext.getEnvironment());
 *                }
 * 		if (bean instanceof EmbeddedValueResolverAware) {
 * 			((EmbeddedValueResolverAware) bean).setEmbeddedValueResolver(this.embeddedValueResolver);
 *        }
 * 		if (bean instanceof ResourceLoaderAware) {
 * 			((ResourceLoaderAware) bean).setResourceLoader(this.applicationContext);
 *        }
 * 		if (bean instanceof ApplicationEventPublisherAware) {
 * 			((ApplicationEventPublisherAware) bean).setApplicationEventPublisher(this.applicationContext);
 *        }
 * 		if (bean instanceof MessageSourceAware) {
 * 			((MessageSourceAware) bean).setMessageSource(this.applicationContext);
 *        }
 * 		if (bean instanceof ApplicationContextAware) {
 * 			((ApplicationContextAware) bean).setApplicationContext(this.applicationContext);
 *        }
 *      }
 *
 * @date: 2020年10月23日 17:38
 * @author: qinrenchuan
 */
public class MyExtensionAgApplicationContextAwareProcessor  {


    /*
     private void invokeAwareInterfaces(Object bean) {

        if (bean instanceof EnvironmentAware) {
            ((EnvironmentAware) bean).setEnvironment(this.applicationContext.getEnvironment());
        }
        if (bean instanceof EmbeddedValueResolverAware) {
            ((EmbeddedValueResolverAware) bean).setEmbeddedValueResolver(this.embeddedValueResolver);
        }
        if (bean instanceof ResourceLoaderAware) {
            ((ResourceLoaderAware) bean).setResourceLoader(this.applicationContext);
        }
        if (bean instanceof ApplicationEventPublisherAware) {
            ((ApplicationEventPublisherAware) bean).setApplicationEventPublisher(this.applicationContext);
        }
        if (bean instanceof MessageSourceAware) {
            ((MessageSourceAware) bean).setMessageSource(this.applicationContext);
        }
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(this.applicationContext);
        }
    }
    */

    // EnvironmentAware：用于获取EnviromentAware的一个扩展类，这个变量非常有用， 可以获得系统内的所有参数。
    //  当然个人认为这个Aware没必要去扩展，因为spring内部都可以通过注入的方式来直接获得。

    // EmbeddedValueResolverAware：用于获取StringValueResolver的一个扩展类， StringValueResolver用于
    // 获取基于String类型的properties的变量，一般我们都用@Value的方式去获取，如果实现了这个Aware接口，
    // 把StringValueResolver缓存起来，通过这个类去获取String类型的变量，效果是一样的。

    // ResourceLoaderAware：用于获取ResourceLoader的一个扩展类，ResourceLoader可以用于获取classpath内所有的资源对象，
    // 可以扩展此类来拿到ResourceLoader对象。

    // ApplicationEventPublisherAware：用于获取ApplicationEventPublisher的一个扩展类，
    // ApplicationEventPublisher可以用来发布事件，结合ApplicationListener来共同使用。
    // 这个对象也可以通过spring注入的方式来获得。

    // MessageSourceAware：用于获取MessageSource的一个扩展类，MessageSource主要用来做国际化。

    // ApplicationContextAware：用来获取ApplicationContext的一个扩展类，ApplicationContext应该
    // 是很多人非常熟悉的一个类了，就是spring上下文管理器，可以手动的获取任何在spring上下文注册的bean，
    // 我们经常扩展这个接口来缓存spring上下文，包装成静态方法。同时ApplicationContext也
    // 实现了BeanFactory，MessageSource，ApplicationEventPublisher等接口，也可以用来做相关接口的事情。
}
