package com.tdt.wheel.redis.controller;

import com.tdt.wheel.redis.annotation.NotRepeatSubmit;
import com.tdt.wheel.redis.domain.response.AccessToken;
import com.tdt.wheel.redis.domain.response.ApiResponse;
import com.tdt.wheel.redis.domain.response.AppInfo;
import com.tdt.wheel.redis.domain.response.TokenInfo;
import com.tdt.wheel.redis.domain.response.UserInfo;
import com.tdt.wheel.redis.util.MD5Util;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: TokenController
 * @since 2020/05/19 00:13
 */
@Slf4j
@RestController
@RequestMapping("/service/token")
public class TokenController {

	@Autowired
	private RedisTemplate redisTemplate;

	@PostMapping("/api_token")
	public ApiResponse<AccessToken> apiToken(String appId,
			@RequestHeader("timestamp")String timestamp,
			@RequestHeader("sign")String sign) {
		Assert.isTrue(!StringUtils.isEmpty(appId)
				&& !StringUtils.isEmpty(timestamp)
				&& !StringUtils.isEmpty(sign),
				"参数错误");

		long requestInterval = System.currentTimeMillis() - Long.valueOf(timestamp);
		Assert.isTrue(requestInterval < 5 * 60 * 1000,
				"请求过期， 请重新请求");

		// 1.根据appId查询数据库获取appSecret
		AppInfo appInfo = new AppInfo("1", "12345678954556");

		// 2. 校验签名
		String signString = timestamp + appId + appInfo.getKey();
		String signature = MD5Util.encode(signString);
		log.info(signature);
		Assert.isTrue(signature.equals(sign),  "签名错误");

		// 3. 如果正确生成一个token保存到redis中，如果错误返回错误信息
		AccessToken accessToken = this.saveToken(0, appInfo, null);

		return ApiResponse.success(accessToken);
	}

	private AccessToken saveToken(int tokenType, AppInfo appInfo, UserInfo userInfo) {
		String token = UUID.randomUUID().toString();

		// Token有效期为2小时
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.SECOND, 7200);
		Date expireTime = calendar.getTime();

		// 保存token
		ValueOperations valueOperations = redisTemplate.opsForValue();
		TokenInfo tokenInfo = new TokenInfo();
		tokenInfo.setTokenType(tokenType);
		tokenInfo.setAppInfo(appInfo);

		if (tokenType == 1) {
			tokenInfo.setUserInfo(userInfo);
		}

		valueOperations.set(token, tokenInfo, 7200, TimeUnit.SECONDS);

		AccessToken accessToken = new AccessToken(token, expireTime);

		return accessToken;
	}

	@NotRepeatSubmit(5000)
	@PostMapping("/user_token")
	public ApiResponse<UserInfo> userToken(String username, String password) {
		// 根据用户名查询密码, 并比较密码(密码可以RSA加密一下)
		UserInfo userInfo = new UserInfo(username, "81255cb0dca1a5f304328a70ac85dcbd", "111111");
		String pwd = password + userInfo.getSalt();
		String passwordMD5 = MD5Util.encode(pwd);
		Assert.isTrue(passwordMD5.equals(userInfo.getPassword()), "密码错误");

		// 保存Token
		AppInfo appInfo = new AppInfo("1", "12345678954556");
		AccessToken accessToken = this.saveToken(1, appInfo, userInfo);
		userInfo.setAccessToken(accessToken);

		return ApiResponse.success(userInfo);
	}

	public static void main(String[] args) {
		long timestamp = System.currentTimeMillis();
		System.out.println(timestamp);
		String signString = timestamp + "1" + "12345678954556";
		String sign = MD5Util.encode(signString);
		System.out.println(sign);

		System.out.println("-------------------");
		signString = "password=123456&username=1&12345678954556" + "ff03e64b-427b-45a7-b78b-47d9e8597d3b1529815393153sdfsdfsfs" + timestamp + "A1scr6";
		sign = MD5Util.encode(signString);
		System.out.println(sign);

	}
}
