package com.tdt.wheel.jwt.util;

import com.tdt.wheel.jwt.jwt.entity.SelfUserEntity;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @description: Security工具类
 * @author tudoutiao
 * @version v1.0.0
 * @since 2020/07/11 17:23
 */
public class SecurityUtil {
    /**
     * 私有化构造器
     */
    private SecurityUtil(){}

    /**
     * 获取当前用户信息
     */
    public static SelfUserEntity getUserInfo(){
        SelfUserEntity userDetails = (SelfUserEntity) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        return userDetails;
    }
    /**
     * 获取当前用户ID
     */
    public static Long getUserId(){
        return getUserInfo().getUserId();
    }
    /**
     * 获取当前用户账号
     */
    public static String getUserName(){
        return getUserInfo().getUsername();
    }
}
