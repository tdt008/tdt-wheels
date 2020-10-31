package com.tdt.springboot.controller;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * description:
 *  第一个问题：SpringBoot是如何解析请求参数的？首先会创建一系列的消息转换器，然后在创建一系列的处理器，处理器中包含了消息转换器，在请求的时候找到对应的处理器，然后根据请求类型找到对应的转换器，进行参数解析
 *  第二个问题：SpringBoot是如何处理请求返回值？同样是消息转换器，返回值处理器，根据返回的标识选择对应的处理器，找到相应的消息转换器，然后使用消息转换器往客户端写数据。
 *
 * @date: 2020年10月31日 16:40
 * @author: qinrenchuan
 */
public class Person2Controller implements PersonApi {

    private static List<Person> personList = new ArrayList<>();

    static {
        personList.add(new Person(10001, "test1"));
        personList.add(new Person(10002, "test2"));
        personList.add(new Person(10003, "test3"));
        personList.add(new Person(10004, "test4"));
        personList.add(new Person(10005, "test5"));
    }


    @Override
    public List<Person> list() {
        return personList;
    }

    @Override
    public Person get(Integer id) {
        Person defaultPerson = new Person(88888, "default");
        return personList.stream()
                .filter(person -> Objects.equals(person.getId(), id))
                .findFirst().orElse(defaultPerson);
    }

    @Override
    public List<Person> add(@Valid Person person) {
        personList.add(person);
        return personList;
    }

    @Override
    public List<Person> update(Person person) {
        personList.removeIf(p -> Objects.equals(p.getId(), person.getId()));
        personList.add(person);
        return personList;
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
