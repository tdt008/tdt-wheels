package com.tdt.springboot.aop;

import java.lang.annotation.*;
/**
 * 自定义注解
 * @date 2020/10/28/0028 14:45
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
}
