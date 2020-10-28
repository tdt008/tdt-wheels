package com.tdt.springboot.service.autowired.impl;

import com.tdt.springboot.service.autowired.PersonService;
import org.springframework.stereotype.Service;

/**
 * description: StudentServiceImpl
 *
 * @date: 2020年10月26日 14:54
 * @author:
 */
@Service(value = "teacherService")
public class TeacherServiceImpl implements PersonService {

    @Override
    public String hello(String name) {
        return "[teacher service] hello " + name;
    }
}
