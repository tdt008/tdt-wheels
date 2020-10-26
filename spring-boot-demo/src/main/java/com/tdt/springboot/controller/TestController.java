package com.tdt.springboot.controller;

import com.tdt.springboot.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Priority;


/**
 * description: Bean实例化完成后，填充Bean
 *      调用AutowiredAnnotationBeanPostProcessor#postProcessProperties处理属性
 *      获取所有需要注入的属性
 *      根据注入属性的类型从IOC容器中查找匹配实例
 *      如果匹配实例存在多个，根据primary属性--->javax.annotation.Priority注解--->注入属性名称依次过滤，
 *          返回符合条件的Bean名称
 *      过滤之后，存在一个符合条件的Bean名称，则返回对应的实例，否则抛出异常
 *
 *
 *      回顾一下开头的2个问题：
 *
 *          Spring Boot何时注入@Autowired标注的属性？
 *          如果注入类型的Bean存在多个Spring Boot是如何处理的？
 *
 *          第一个问题：是在Bean实例化后，填充Bean的时候注入@Autowired标注的属性
 *
 *          第二个问题：如果存在多个类型的Bean，会根据primary--->javax.annotation.Priority--->名称依次过滤，
 *              得到最终匹配的bean名称
 *
 * @date: 2020年10月26日 14:57
 * @author: qinrenchuan
 */
@RestController
public class TestController {
    @Autowired
    private PersonService studentService;

    @Autowired
    private PersonService teacherService;

    // @Autowired
    //private List<PersonService> personServices;





    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name") String name) {
        return studentService.hello(name) + "=======>" + teacherService.hello(name);
    }
}
