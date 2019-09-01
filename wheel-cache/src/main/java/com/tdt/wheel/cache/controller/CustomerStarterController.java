package com.tdt.wheel.cache.controller;/**
 * Created by rc on 2019/9/1.
 */

import com.tdt.wheel.starter.service.TdtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qrc
 * @description 测试自定义starter
 * @date 2019/9/1
 */
@RestController
@RequestMapping("/customerStarter")
public class CustomerStarterController {

    @Autowired
    private TdtService tdtService;

    @GetMapping("/hi")
    public String sayHello() {
        return tdtService.sayHello();
    }

}
