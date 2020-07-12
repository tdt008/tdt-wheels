package com.tdt.wheel.jwt.jwt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: JWTConfig
 * @since 2020/07/11 17:20
 */
@Configuration
public class JWTConfig {
    /**
     * 密钥KEY
     */
    @Value("${jwt.secret}")
    private String secret;
    /**
     * TokenKey
     */
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    /**
     * Token前缀字符
     */
    @Value("${jwt.tokenPrefix}")
    private String tokenPrefix;
    /**
     * 过期时间
     */
    @Value("${jwt.expiration}")
    private Integer expiration;
    /**
     * 不需要认证的接口
     */
    @Value("${jwt.antMatchers}")
    private String antMatchers;

    public String getSecret() {
        return secret;
    }

    public String getTokenHeader() {
        return tokenHeader;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public Integer getExpiration() {
        return expiration;
    }

    public String getAntMatchers() {
        return antMatchers;
    }
}
