package com.tdt.wheel.jwt.jwt.handler;

import com.tdt.wheel.jwt.util.ResultUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * @description: 用户未登录处理类
 * @author tudoutiao
 * @version v1.0.0
 * @since 2020/07/11 16:57
 */
@Component
public class UserAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    /**
     * @description 用户未登陆返回结果
     * @param request
     * @param response
     * @param exception
     * @author tudoutiao
     * @date 16:58 2020/7/11
     **/
    @Override
    public void commence(HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception){
        ResultUtil.responseJson(response,ResultUtil.resultCode(401, "未登录"));
    }
}
