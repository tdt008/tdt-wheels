package com.tdt.springboot.service.systemlog;

import com.tdt.springboot.aop.SystemLog;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * description: SystemLogServiceImpl
 *
 * @date: 2020年10月28日 14:43
 */
@Service
public class SystemLogService {

    @SystemLog
    public String log(String name) throws InterruptedException {
        System.out.println("执行业务方法");
        TimeUnit.SECONDS.sleep(1);
        return "hello " + name;
    }
}
