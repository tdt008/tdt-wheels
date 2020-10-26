package com.tdt.springboot.controller;

import com.tdt.springboot.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * description: TestController
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
