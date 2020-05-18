package com.tdt.wheel.redis.domain.response;

import lombok.Data;

/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: TokenInfo
 * @since 2020/05/19 00:45
 */
@Data
public class TokenInfo {
	/** token类型: api:0 、user:1 */
	private Integer tokenType;
	/** App 信息 */
	private AppInfo appInfo;
	/** 用户其他数据 */
	private UserInfo userInfo;
}
