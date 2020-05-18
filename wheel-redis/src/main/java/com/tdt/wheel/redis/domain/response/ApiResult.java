package com.tdt.wheel.redis.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: ApiResult
 * @since 2020/05/19 00:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResult {
	/** 代码 */
	private String code;
	/** 结果 */
	private String msg;
}
