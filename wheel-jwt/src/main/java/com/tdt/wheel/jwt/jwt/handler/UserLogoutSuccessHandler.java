package com.tdt.wheel.jwt.jwt.handler;

import com.tdt.wheel.jwt.util.ResultUtil;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: UserLogoutSuccessHandler
 * @since 2020/07/11 17:02
 */
@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {

    /**
     * @description  用户登出返回结果.这里应该让前端清除掉Token
     * @param request
     * @param response
     * @param authentication
     * @author tudoutiao
     * @date 17:03 2020/7/11
     **/
    @Override
    public void onLogoutSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication){
        Map<String,Object> resultData = new HashMap<>();
        resultData.put("code", "200");
        resultData.put("msg", "登出成功");
        SecurityContextHolder.clearContext();
        ResultUtil.responseJson(response, ResultUtil.resultSuccess(resultData));
    }
}
