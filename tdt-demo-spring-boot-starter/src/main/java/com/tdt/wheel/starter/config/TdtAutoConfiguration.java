package com.tdt.wheel.starter.config;/**
 * Created by rc on 2019/9/1.
 */

import com.tdt.wheel.starter.service.TdtService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author qrc
 * @description 自动配置类
 * @date 2019/9/1
 */
@Configuration
//使@ConfigurationProperties注解生效
@EnableConfigurationProperties(TdtDemoConfig.class)
public class TdtAutoConfiguration {

    @Bean
    public TdtService getTdtService(TdtDemoConfig tdtDemoConfig) {
        TdtService tdtService = new TdtService(tdtDemoConfig);
        return tdtService;
    }
}
