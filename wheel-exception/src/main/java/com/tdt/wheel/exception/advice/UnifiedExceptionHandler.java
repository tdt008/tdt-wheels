package com.tdt.wheel.exception.advice;

import com.tdt.wheel.exception.exception.BaseException;
import com.tdt.wheel.exception.exception.BusinessException;
import com.tdt.wheel.exception.response.constant.ArgumentResponseEnum;
import com.tdt.wheel.exception.response.constant.CommonResponseEnum;
import com.tdt.wheel.exception.response.ErrorResponse;
import com.tdt.wheel.exception.response.constant.ServletResponseEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: UnifiedExceptionHandler
 * @since 2020/05/12 23:14
 */
@Component
@ControllerAdvice
@ConditionalOnMissingBean(UnifiedExceptionHandler.class)
public class UnifiedExceptionHandler {

	Logger log =LoggerFactory.getLogger(UnifiedExceptionHandler.class);

	/** 生产环境 */
	private final static String ENV_PROD= "prod";

	@Value("${spring.profiles.active}")
	private String profile;


	/**
	 * 业务异常  --- 自定义异常
	 *
	 * @param e 异常
	 * @return 异常结果
	 */
	@ExceptionHandler(value = BusinessException.class)
	@ResponseBody
	public ErrorResponse handleBusinessException(BaseException e) {
		log.error(e.getMessage(), e);

		return new ErrorResponse(e.getCode(), e.getMessage());
	}

	/**
	 * 自定义异常
	 *
	 * @param e 异常
	 * @return 异常结果
	 */
	@ExceptionHandler(value = BaseException.class)
	@ResponseBody
	public ErrorResponse handleBaseException(BaseException e) {
		log.error(e.getMessage(), e);

		return new ErrorResponse(e.getResponseEnum().getCode(), e.getMessage());
	}

	/**
	 * Controller上一层相关异常   ---- 进入Controller前的异常
	 *
	 * @param e 异常
	 * @return 异常结果
	 */
	@ExceptionHandler({
			NoHandlerFoundException.class,
			HttpRequestMethodNotSupportedException.class,
			HttpMediaTypeNotSupportedException.class,
			MissingPathVariableException.class,
			MissingServletRequestParameterException.class,
			TypeMismatchException.class,
			HttpMessageNotReadableException.class,
			HttpMessageNotWritableException.class,
			// BindException.class,
			// MethodArgumentNotValidException.class
			HttpMediaTypeNotAcceptableException.class,
			ServletRequestBindingException.class,
			ConversionNotSupportedException.class,
			MissingServletRequestPartException.class,
			AsyncRequestTimeoutException.class
	})
	@ResponseBody
	public ErrorResponse handleServletException(Exception e) {
		log.error(e.getMessage(), e);
		int code = CommonResponseEnum.SERVER_ERROR.getCode();
		try {
			ServletResponseEnum servletExceptionEnum = ServletResponseEnum.valueOf(e.getClass().getSimpleName());
			code = servletExceptionEnum.getCode();
		} catch (IllegalArgumentException e1) {
			log.error("class [{}] not defined in enum {}", e.getClass().getName(), ServletResponseEnum.class.getName());
		}

		if (ENV_PROD.equals(profile)) {
			// 当为生产环境, 不适合把具体的异常信息展示给用户, 比如404.
			code = CommonResponseEnum.SERVER_ERROR.getCode();
			BaseException baseException = new BaseException(CommonResponseEnum.SERVER_ERROR);
			String message = baseException.getMessage();
			return new ErrorResponse(code, message);
		}

		return new ErrorResponse(code, e.getMessage());
	}

	/**
	 * 参数绑定异常----进入Controller前的异常
	 *
	 * @param e 异常
	 * @return 异常结果
	 */
	@ExceptionHandler(value = BindException.class)
	@ResponseBody
	public ErrorResponse handleBindException(BindException e) {
		log.error("参数绑定校验异常", e);

		return wrapperBindingResult(e.getBindingResult());
	}

	/**
	 * 参数校验异常，将校验失败的所有异常组合成一条错误信息   -----   进入Controller前的异常
	 *
	 * @param e 异常
	 * @return 异常结果
	 */
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseBody
	public ErrorResponse handleValidException(MethodArgumentNotValidException e) {
		log.error("参数绑定校验异常", e);

		return wrapperBindingResult(e.getBindingResult());
	}

	/**
	 * 包装绑定异常结果
	 *
	 * @param bindingResult 绑定结果
	 * @return 异常结果
	 */
	private ErrorResponse wrapperBindingResult(BindingResult bindingResult) {
		StringBuilder msg = new StringBuilder();

		for (ObjectError error : bindingResult.getAllErrors()) {
			msg.append(", ");
			if (error instanceof FieldError) {
				msg.append(((FieldError) error).getField()).append(": ");
			}
			msg.append(error.getDefaultMessage() == null ? "" : error.getDefaultMessage());

		}

		return new ErrorResponse(ArgumentResponseEnum.VALID_ERROR.getCode(), msg.substring(2));
	}

	/**
	 * 未定义异常（未知异常）
	 *
	 * @param e 异常
	 * @return 异常结果
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ErrorResponse handleException(Exception e) {
		log.error(e.getMessage(), e);

		if (ENV_PROD.equals(profile)) {
			// 当为生产环境, 不适合把具体的异常信息展示给用户, 比如数据库异常信息.
			int code = CommonResponseEnum.SERVER_ERROR.getCode();
			BaseException baseException = new BaseException(CommonResponseEnum.SERVER_ERROR);
			return new ErrorResponse(code, baseException.getMessage());
		}

		return new ErrorResponse(CommonResponseEnum.SERVER_ERROR.getCode(), e.getMessage());
	}

}
