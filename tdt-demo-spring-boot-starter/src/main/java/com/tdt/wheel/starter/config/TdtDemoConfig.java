package com.tdt.wheel.starter.config;/**
 * Created by rc on 2019/9/1.
 */

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author qrc
 * @description 使用ConfigurationProperties注解可将配置文件（yml/properties）中指定前缀的配置转为bean
 * @date 2019/9/1
 */
@ConfigurationProperties(prefix = "tdt-config")
public class TdtDemoConfig {
    private String appid;
    private String accountSid;
    private String authToken;

    public String getAppid() {
        return appid;
    }

    public TdtDemoConfig setAppid(String appid) {
        this.appid = appid;
        return this;
    }

    public String getAccountSid() {
        return accountSid;
    }

    public TdtDemoConfig setAccountSid(String accountSid) {
        this.accountSid = accountSid;
        return this;
    }

    public String getAuthToken() {
        return authToken;
    }

    public TdtDemoConfig setAuthToken(String authToken) {
        this.authToken = authToken;
        return this;
    }

    @Override
    public String toString() {
        return "TdtDemoConfig{" +
                "appid='" + appid + '\'' +
                ", accountSid='" + accountSid + '\'' +
                ", authToken='" + authToken + '\'' +
                '}';
    }
}
