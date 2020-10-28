package com.tdt.springboot.aop;

import org.springframework.aop.framework.autoproxy.InfrastructureAdvisorAutoProxyCreator;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

/**
 * description: ProxySystemLogConfiguration
 *
 * @date: 2020年10月28日 14:35
 */
@Configuration
public class ProxySystemLogConfiguration {
    /**
     * 定义切面
     * 此处一定要指定Role注解
     *
     * @return com.tdt.springboot.aop.BeanFactorySystemLogAdvisor
     * @date 2020/10/28/0028 14:39
     */
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    @Bean
    public BeanFactorySystemLogAdvisor beanFactorySystemLogAdvisor() {
        BeanFactorySystemLogAdvisor beanFactorySystemLogAdvisor = new BeanFactorySystemLogAdvisor();
        beanFactorySystemLogAdvisor.setAdvice(systemLogInterceptor());
        return beanFactorySystemLogAdvisor;
    }

    /**
     * 定义通知
     * @return com.tdt.springboot.aop.SystemLogInterceptor
     * @date 2020/10/28/0028 14:37
     */
    @Bean
    public SystemLogInterceptor systemLogInterceptor() {
        return new SystemLogInterceptor();
    }

    /**
     * 一定要声明InfrastructureAdvisorAutoProxyCreator，用于实现bean的后置处理
     * @return org.springframework.aop.framework.autoproxy.InfrastructureAdvisorAutoProxyCreator
     * @date 2020/10/28/0028 14:40
     */
    @Bean
    public InfrastructureAdvisorAutoProxyCreator infrastructureAdvisorAutoProxyCreator() {
        return new InfrastructureAdvisorAutoProxyCreator();
    }
}
