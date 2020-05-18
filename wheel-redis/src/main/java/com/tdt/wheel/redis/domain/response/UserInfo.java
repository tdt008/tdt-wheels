package com.tdt.wheel.redis.domain.response;

import lombok.Data;

/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: UserInfo
 * @since 2020/05/19 00:40
 */
@Data
public class UserInfo {
	/** 用户名 */
	private String username;
	/** 手机号 */
	private String mobile;
	/** 邮箱 */
	private String email;
	/** 密码 */
	private String password;
	/** 盐 */
	private String salt;


	private AccessToken accessToken;


	public UserInfo(String username, String password, String salt) {
		this.username = username;
		this.password = password;
		this.salt = salt;
	}
}
