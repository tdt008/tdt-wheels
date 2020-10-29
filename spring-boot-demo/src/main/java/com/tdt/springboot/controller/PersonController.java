package com.tdt.springboot.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * description:
 *      为什么我们在控制器中添加一个方法，使用@RequestMapping注解标注，
 *      指定一个路径，就可以用来处理一个web请求？因为在初始化过程中，会将请求路径和处理方法进行绑定，
 *      我们在请求ulr的时候，匹配到我们对应的处理方法，然后调用处理方法，就可以执行此次的ewb请求了。
 *
 *      如果多个方法的请求路径一致，Spring Boot是如何处理的？如果多个方法的请求路径一致，
 *      会拿请求方法、请求参数、请求header等，最终会匹配出最符合的一个处理方法，如果匹配出多个结果，就会报错。
 *
 *
 *      RequestMappingHandlerMapping对象创建总结
 *          调用afterPropertiesSet初始化方法
 *          获取所有的bean名称
 *          遍历所有的bean名称，如果bean名称不是以”scopedTarget.“开头就继续处理
 *          根据bean名称获取bean类型，获取对应的类型上是否含有@Controller注解或@RequestMapping注解，如果有就继续处理
 *          获取当前类中所有的方法，遍历所有的方法
 *          根据Method对象生成RequestMappingInfo对象，如果类上也很有@RequestMapping注解，那么也生成RequestMappingInfo对象，
 *          将这两个RequestMappingInfo对象进行合并
 *          遍历Method、RequestMappingInfo对应的map集合并注册到对应的mappingLookup、urlLookup、registry集合中
 *
 * @date: 2020年10月29日 19:40
 * @author: qinrenchuan
 */
@RestController
public class PersonController {

    private static List<Person> personList = new ArrayList<>();

    static {
        personList.add(new Person(10001, "test1"));
        personList.add(new Person(10002, "test2"));
        personList.add(new Person(10003, "test3"));
        personList.add(new Person(10004, "test4"));
        personList.add(new Person(10005, "test5"));
    }

    @GetMapping("/")
    public List<Person> list() {
        return personList;
    }

    @GetMapping("/{id}")
    public Person get(@PathVariable("id") Integer id) {
        Person defaultPerson = new Person(88888, "default");
        return personList.stream().filter(person -> Objects.equals(person.getId(), id)).findFirst().orElse(defaultPerson);
    }

    @PostMapping("/")
    public void add(@RequestBody Person person) {
        personList.add(person);
    }

    @PutMapping("/")
    public void update(@RequestBody Person person) {
        personList.removeIf(p -> Objects.equals(p.getId(), person.getId()));
        personList.add(person);
    }

    public static class Person {
        private Integer id;
        private String name;

        public Person(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public Person setId(Integer id) {
            this.id = id;
            return this;
        }

        public String getName() {
            return name;
        }

        public Person setName(String name) {
            this.name = name;
            return this;
        }
    }
}
