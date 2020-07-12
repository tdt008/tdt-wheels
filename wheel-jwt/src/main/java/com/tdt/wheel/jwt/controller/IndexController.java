package com.tdt.wheel.jwt.controller;

import com.tdt.wheel.jwt.util.ResultUtil;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: IndexController
 * @since 2020/07/11 17:25
 */
@RestController
@RequestMapping("/index")
public class IndexController {
    /**
     * 首页
     * @Return Map<String,Object> 返回数据MAP
     */
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public Map<String,Object> userLogin(){
        // 组装参数
        Map<String,Object> result = new HashMap<>();
        result.put("title","这里是首页不需要权限和登录拦截");
        return ResultUtil.resultSuccess(result);
    }

}
