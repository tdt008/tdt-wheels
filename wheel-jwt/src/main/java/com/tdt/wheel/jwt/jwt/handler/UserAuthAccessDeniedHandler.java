package com.tdt.wheel.jwt.jwt.handler;

import com.tdt.wheel.jwt.util.ResultUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * @description: 暂无权限处理类
 * @author tudoutiao
 * @version v1.0.0
 * @since 2020/07/11 16:56
 */
@Component
public class UserAuthAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * @description 暂无权限返回结果
     * @param request
     * @param response
     * @param exception
     * @author tudoutiao
     * @date 16:57 2020/7/11
     **/
    @Override
    public void handle(HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException exception){
        ResultUtil.responseJson(response,ResultUtil.resultCode(403, "未授权"));
    }
}
