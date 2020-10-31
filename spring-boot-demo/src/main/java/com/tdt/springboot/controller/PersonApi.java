package com.tdt.springboot.controller;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * description: PersonApi
 *
 * @date: 2020年10月31日 16:39
 */
@RequestMapping("/persons")
public interface PersonApi {
    /**
     * list
     * @return java.util.List<com.tdt.springboot.controller.Person2Controller.Person>
     * @date 2020/10/31/0031 16:41
     */
    @GetMapping("/")
    List<Person2Controller.Person> list();

    /**
     * get
     * @param id
     * @return com.tdt.springboot.controller.Person2Controller.Person
     * @date 2020/10/31/0031 16:41
     */
    @GetMapping("/{id}")
    Person2Controller.Person get(@PathVariable("id") Integer id);

    /**
     * add
     * @param person
     * @return java.util.List<com.tdt.springboot.controller.Person2Controller.Person>
     * @date 2020/10/31/0031 16:41
     */
    @PostMapping("/")
    List<Person2Controller.Person> add(@Valid @RequestBody Person2Controller.Person person);

    /**
     * update
     * @param person
     * @return java.util.List<com.tdt.springboot.controller.Person2Controller.Person>
     * @date 2020/10/31/0031 16:41
     */
    @PutMapping("/")
    List<Person2Controller.Person> update(@RequestBody Person2Controller.Person person);
}
