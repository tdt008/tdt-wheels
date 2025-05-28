package com.tdt.boot.version.controller.v2;

import com.tdt.boot.version.config.version.ApiVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: TODO
 * @date: 2022/2/18 11:02
 * @author: renchuangege
 */
@RequestMapping("/{version}/order")
@RestController
@ApiVersion(2)
public class OrderController {


    @GetMapping("/getOrder")
    public String getOrder() {
        return "v2-order";
    }
}
