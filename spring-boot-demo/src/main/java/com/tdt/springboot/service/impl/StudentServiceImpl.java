package com.tdt.springboot.service.impl;

import com.tdt.springboot.service.PersonService;
import org.springframework.stereotype.Service;


/**
 * description: StudentServiceImpl
 *
 * @date: 2020年10月26日 14:54
 * @author: qinrenchuan
 */
@Service(value = "studentService")
public class StudentServiceImpl implements PersonService {

    @Override
    public String hello(String name) {
        return "[student service] hello " + name;
    }
}
