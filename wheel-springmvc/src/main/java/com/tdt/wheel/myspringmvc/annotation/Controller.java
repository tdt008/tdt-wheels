package com.tdt.wheel.myspringmvc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 控制层注解
 * @version v1.0.0
 * @description: Controller
 * @since 2020/05/17 12:13
 */
// java doc
@Documented
// 作用于类上
@Target(ElementType.TYPE)
// 限制注解的生命周期
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
	/**
	 * @description  作用于该类上的注解有一个value属性，就是contrler的名称
	 * @return java.lang.String
	 * @author tudoutiao
	 * @date 12:15 2020/5/17
	 **/
	String value();
}
