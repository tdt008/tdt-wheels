package com.java.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "/index")
    public String index(){
        return "index:" + System.currentTimeMillis();
    }

    @RequestMapping(value = "/indexxx")
    public String indexxx() {
        try {
            for (int i = 0; i < 1000; i++) {
                logger.info("indexxx----------" + i);
            }
            int b = 5 / 0;
        } catch (Exception e) {
            logger.error("error----index....", e);
        }
        return "fail";
    }
}
