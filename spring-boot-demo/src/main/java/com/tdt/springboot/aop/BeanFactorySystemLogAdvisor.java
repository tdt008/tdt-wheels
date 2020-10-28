package com.tdt.springboot.aop;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;

/**
 * description: 定义切面
 *
 * @date: 2020年10月26日 20:50
 */
public class BeanFactorySystemLogAdvisor extends AbstractBeanFactoryPointcutAdvisor {
    /** 定义切点 */
    private final SystemLogPointcut systemLogPointcut = new SystemLogPointcut();

    @Override
    public Pointcut getPointcut() {
        return this.systemLogPointcut;
    }


}
