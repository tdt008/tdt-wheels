package com.tdt.wheel.exception.response;

/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: CommonResponse
 * @since 2020/05/12 23:46
 */
public class CommonResponse<T> extends BaseResponse {
	protected T data;

	public T getData() {
		return data;
	}

	public CommonResponse<T> setData(T data) {
		this.data = data;
		return this;
	}
}
