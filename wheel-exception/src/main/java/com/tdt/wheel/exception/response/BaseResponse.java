package com.tdt.wheel.exception.response;

/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: BaseResponse
 * @since 2020/05/12 23:45
 */
public class BaseResponse {
	protected int code;
	protected String message;

	public BaseResponse() {
	}

	public BaseResponse(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public BaseResponse setCode(int code) {
		this.code = code;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public BaseResponse setMessage(String message) {
		this.message = message;
		return this;
	}
}
