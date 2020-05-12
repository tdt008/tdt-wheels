package com.tdt.wheel.exception.exception;


import com.tdt.wheel.exception.response.constant.IResponseEnum;
import java.text.MessageFormat;

/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: BusinessExceptionAssert
 * @since 2020/05/12 22:49
 */
public interface BusinessExceptionAssert extends IResponseEnum, Assert {

	@Override
	default BaseException newException(Object... args) {
		String msg = MessageFormat.format(this.getMessage(), args);
		return new BusinessException(this, args, msg);
	}

	@Override
	default BaseException newException(Throwable t, Object... args) {
		String msg = MessageFormat.format(this.getMessage(), args);
		return new BusinessException(this, args, msg, t);
	}

}
