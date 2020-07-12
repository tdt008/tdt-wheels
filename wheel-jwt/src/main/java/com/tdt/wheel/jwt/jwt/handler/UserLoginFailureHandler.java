package com.tdt.wheel.jwt.jwt.handler;

import com.tdt.wheel.jwt.util.ResultUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: UserLoginFailureHandler
 * @since 2020/07/11 16:59
 */
@Component
public class UserLoginFailureHandler implements AuthenticationFailureHandler {

    private static final Logger log = LoggerFactory.getLogger(UserLoginFailureHandler.class);

    /**
     * @description 登陆失败返回结果
     * @param request
     * @param response
     * @param exception
     * @author tudoutiao
     * @date 16:59 2020/7/11
     **/
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception) {

        // 这些对于操作的处理类可以根据不同异常进行不同处理
        if (exception instanceof UsernameNotFoundException){
            log.info("【登录失败】" + exception.getMessage());
            ResultUtil.responseJson(response, ResultUtil.resultCode(500, "用户名不存在"));
        }
        if (exception instanceof LockedException){
            log.info("【登录失败】" + exception.getMessage());
            ResultUtil.responseJson(response, ResultUtil.resultCode(500, "用户被冻结"));
        }
        if (exception instanceof BadCredentialsException){
            log.info("【登录失败】" + exception.getMessage());
            ResultUtil.responseJson(response, ResultUtil.resultCode(500, "用户名密码不正确"));
        }
        ResultUtil.responseJson(response, ResultUtil.resultCode(500, "登录失败"));
    }
}
