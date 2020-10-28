package com.tdt.springboot.aop;

import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 定义切点
 * @date 2020/10/28/0028 14:54
 */
public class SystemLogPointcut extends StaticMethodMatcherPointcut {

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        // 查找类上@SystemLog注解属性
        AnnotationAttributes attributes = AnnotatedElementUtils.findMergedAnnotationAttributes(
                targetClass, SystemLog.class, false, false);
        if (Objects.nonNull(attributes)) {
            return true;
        }

        // 查找方法上@SystemLog注解属性
        attributes = AnnotatedElementUtils.findMergedAnnotationAttributes(
                method, SystemLog.class, false, false);
        return Objects.nonNull(attributes);
    }
}