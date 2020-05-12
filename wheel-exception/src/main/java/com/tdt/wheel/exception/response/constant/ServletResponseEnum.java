package com.tdt.wheel.exception.response.constant;

/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: ServletResponseEnum
 * @since 2020/05/13 00:23
 */
public enum ServletResponseEnum {

	;
	private Integer code;
	private String message;

	ServletResponseEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
