package com.tdt.wheel.exception.response.constant;

import com.tdt.wheel.exception.exception.BusinessExceptionAssert;

/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: ArgumentResponseEnum
 * @since 2020/05/13 00:28
 */
public enum ArgumentResponseEnum implements BusinessExceptionAssert {
	VALID_ERROR(3000, "参数无效")
	;

	private int code;
	private String message;

	ArgumentResponseEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
