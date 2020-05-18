package com.tdt.wheel.redis.domain.response;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: AccessToken
 * @since 2020/05/19 00:23
 */
@Data
@AllArgsConstructor
public class AccessToken {
	/** token */
	private String token;
	/** 失效时间 */
	private Date expireTime;
}
