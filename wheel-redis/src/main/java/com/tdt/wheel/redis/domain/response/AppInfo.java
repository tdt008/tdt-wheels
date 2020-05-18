package com.tdt.wheel.redis.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: AppInfo
 * @since 2020/05/19 00:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppInfo {
	/** App id */
	private String appId;
	/** API 秘钥 */
	private String key;
}
