package com.tdt.wheel.exception.exception;

import com.tdt.wheel.exception.response.constant.IResponseEnum;

/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: BusinessException
 * @since 2020/05/12 22:45
 */
public class BusinessException extends BaseException {

	public BusinessException(IResponseEnum responseEnum, Object[] args, String message) {
		super(responseEnum, args, message);
	}

	public BusinessException(IResponseEnum responseEnum, Object[] args, String message, Throwable cause) {
		super(responseEnum, args, message, cause);
	}
}
