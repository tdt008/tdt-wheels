package com.tdt.springboot.controller;

import com.tdt.springboot.service.systemlog.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: SystemLogController
 *
 * @date: 2020年10月28日 14:41
 */
@RestController
public class SystemLogController {
    @Autowired
    private SystemLogService systemLogService;

    @GetMapping("/log")
    public String hello(@RequestParam("name") String name) throws InterruptedException {
        return systemLogService.log(name);
    }
}
