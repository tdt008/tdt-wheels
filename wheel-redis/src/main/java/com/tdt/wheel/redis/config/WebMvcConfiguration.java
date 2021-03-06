package com.tdt.wheel.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: WebMvcConfiguration
 * @since 2020/05/19 00:55
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

	private static final String[] excludePathPatterns = {"/service/token/api_token"};

	@Autowired
	private TokenInterceptor tokenInterceptor;

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(tokenInterceptor)
				.addPathPatterns("/service/**")
				.excludePathPatterns(excludePathPatterns);
	}
}
