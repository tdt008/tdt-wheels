package com.tdt.wheel.exception.response;

/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: ErrorResponse
 * @since 2020/05/12 23:47
 */
public class ErrorResponse extends BaseResponse {

	public ErrorResponse(Integer code, String message) {
		super(code, message);
	}
}
