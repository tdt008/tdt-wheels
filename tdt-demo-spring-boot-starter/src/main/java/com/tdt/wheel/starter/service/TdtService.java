package com.tdt.wheel.starter.service;/**
 * Created by rc on 2019/9/1.
 */

import com.tdt.wheel.starter.config.TdtDemoConfig;

/**
 * @author qrc
 * @description
 * @date 2019/9/1
 */
public class TdtService {

    private String appid;
    private String accountSid;
    private String authToken;

    public TdtService(TdtDemoConfig tdtDemoConfig) {
        System.out.println(tdtDemoConfig);
        this.appid = tdtDemoConfig.getAppid();
        this.accountSid = tdtDemoConfig.getAccountSid();
        this.authToken = tdtDemoConfig.getAuthToken();
    }

    public String sayHello() {
        String result = String.format("appid=%s, accountSid=%s, authToken=%s", appid, accountSid, authToken);
        // TODO 可以做很多事情
        return result;
    }
}
