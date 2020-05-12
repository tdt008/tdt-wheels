package com.tdt.wheel.exception.response.constant;

import com.tdt.wheel.exception.exception.BusinessExceptionAssert;

/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: ResponseEnum
 * @since 2020/05/12 23:02
 */
public enum ResponseEnum implements BusinessExceptionAssert {
	BAD_USER_TYPE(7001, "Bad user type."),
	USER_NOT_FOUND(7002, "User not found.")
	;


	private int code;
	private String message;

	ResponseEnum(int code, String msg) {
		this.code = code;
		this.message = msg;
	}


	@Override
	public int getCode() {
		return this.code;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
