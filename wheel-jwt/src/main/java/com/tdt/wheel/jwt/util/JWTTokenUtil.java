package com.tdt.wheel.jwt.util;

import com.alibaba.fastjson.JSON;
import com.tdt.wheel.jwt.jwt.config.JWTConfig;
import com.tdt.wheel.jwt.jwt.entity.SelfUserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

/**
 * @description: JWT工具类
 * @author tudoutiao
 * @version v1.0.0
 * @since 2020/07/11 16:55
 */
public class JWTTokenUtil {
    /**
     * 生成Token
     * @Param  selfUserEntity 用户安全实体
     * @Return Token
     */
    public static String createAccessToken(SelfUserEntity selfUserEntity,
            JWTConfig jwtConfig){
        // 登陆成功生成JWT
        String token = Jwts.builder()
                // 放入用户名和用户ID
                .setId(selfUserEntity.getUserId()+"")
                // 主题
                .setSubject(selfUserEntity.getUsername())
                // 签发时间
                .setIssuedAt(new Date())
                // 签发者
                .setIssuer("tdt")
                // 自定义属性 放入用户拥有权限
                .claim("authorities", JSON.toJSONString(selfUserEntity.getAuthorities()))
                // 失效时间
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
                // 签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret())
                .compact();
        return token;
    }
}
