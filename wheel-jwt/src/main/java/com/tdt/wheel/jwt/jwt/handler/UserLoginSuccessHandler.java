package com.tdt.wheel.jwt.jwt.handler;

import com.tdt.wheel.jwt.jwt.config.JWTConfig;
import com.tdt.wheel.jwt.jwt.entity.SelfUserEntity;
import com.tdt.wheel.jwt.util.JWTTokenUtil;
import com.tdt.wheel.jwt.util.ResultUtil;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @description: 登陆成功
 * @author tudoutiao
 * @version v1.0.0
 * @since 2020/07/11 17:00
 */
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JWTConfig jwtConfig;

    /**
     * @description 登陆成功返回结果
     * @param request
     * @param response
     * @param authentication
     * @author tudoutiao
     * @date 17:02 2020/7/11
     **/
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) {
        // 组装JWT
        SelfUserEntity selfUserEntity =  (SelfUserEntity) authentication.getPrincipal();
        String token = JWTTokenUtil.createAccessToken(selfUserEntity, jwtConfig);
        token = jwtConfig.getTokenPrefix() + token;
        // 封装返回参数
        Map<String,Object> resultData = new HashMap<>();
        resultData.put("code","200");
        resultData.put("msg", "登录成功");
        resultData.put("token",token);
        ResultUtil.responseJson(response,resultData);
    }
}
