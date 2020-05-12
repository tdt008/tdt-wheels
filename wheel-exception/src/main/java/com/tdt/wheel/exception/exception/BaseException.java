package com.tdt.wheel.exception.exception;

import com.tdt.wheel.exception.response.constant.IResponseEnum;

/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: BaseException
 * @since 2020/05/12 22:46
 */
public class BaseException extends RuntimeException {
	private Integer code;
	private String message;
	private IResponseEnum responseEnum;

	public BaseException(IResponseEnum responseEnum) {
		super(responseEnum.getMessage());
		this.code = responseEnum.getCode();
		this.message = responseEnum.getMessage();
		this.responseEnum = responseEnum;
	}

	public BaseException(IResponseEnum responseEnum, Object[] args, String message) {
		super(message);
		this.code = responseEnum.getCode();
		this.message = responseEnum.getMessage();
		this.responseEnum = responseEnum;
	}

	public BaseException(IResponseEnum responseEnum, Object[] args, String message, Throwable cause) {
		super(message, cause);
		this.code = responseEnum.getCode();
		this.message = responseEnum.getMessage();
		this.responseEnum = responseEnum;
	}

	public IResponseEnum getResponseEnum() {
		return responseEnum;
	}

	public Integer getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
