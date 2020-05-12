package com.tdt.wheel.exception.response.constant;

import com.tdt.wheel.exception.exception.BusinessExceptionAssert;

/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: CommonResponseEnum
 * @since 2020/05/13 00:02
 */
public enum  CommonResponseEnum implements BusinessExceptionAssert {
	SUCCESS(2000, "SUCCESS"),
	SERVER_ERROR(4000, "SERVER ERROR")

	;
	private int code;
	private String message;

	CommonResponseEnum(int code, String msg) {
		this.code = code;
		this.message = msg;
	}


	@Override
	public int getCode() {
		return 0;
	}

	@Override
	public String getMessage() {
		return null;
	}
}
