package com.tdt.wheel.exception.exception;


/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: Assert
 * @since 2020/05/12 22:52
 */
public interface Assert {
	/**
	 * @description 创建异常
	 * @param args
	 * @return com.tdt.wheel.exception.exception.BaseException
	 * @author tudoutiao
	 * @date 22:55 2020/5/12
	 **/
	BaseException newException(Object... args);

	/**
	 * @description 创建异常
	 * @param t
	 * @param args
	 * @return com.tdt.wheel.exception.exception.BaseException
	 * @author tudoutiao
	 * @date 22:55 2020/5/12
	 **/
	BaseException newException(Throwable t, Object... args);

	default void assertNotNull(Object obj) {
		if (obj == null) {
			throw newException(obj);
		}
	}

	default void assertNotNull(Object obj, Object... args) {
		if (obj == null) {
			throw newException(args);
		}
	}
}
