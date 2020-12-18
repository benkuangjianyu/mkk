package com.cloud.mkk.common.sentinel.handle;

import com.alibaba.csp.sentinel.Tracer;
import com.cloud.mkk.common.core.util.ResponseRet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 全局异常处理器结合sentinel 全局异常处理器不能作用在 oauth server
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Slf4j
@RestControllerAdvice
@ConditionalOnExpression("!'${security.oauth2.client.clientId}'.isEmpty()")
public class GlobalBizExceptionHandler {

	/**
	 * 全局异常.
	 * @param e the e
	 * @return R
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseRet handleGlobalException(Exception e) {
		log.error("全局异常信息 ex={}", e.getMessage(), e);

		// 业务异常交由 sentinel 记录
		Tracer.trace(e);
		return ResponseRet.failed(e.getLocalizedMessage());
	}

	/**
	 * AccessDeniedException
	 * @param e the e
	 * @return R
	 */
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ResponseRet handleAccessDeniedException(AccessDeniedException e) {
		String msg = SpringSecurityMessageSource.getAccessor().getMessage("AbstractAccessDecisionManager.accessDenied",
				e.getMessage());
		log.error("拒绝授权异常信息 ex={}", msg, e);
		return ResponseRet.failed(e.getLocalizedMessage());
	}

	/**
	 * validation Exception
	 * @param exception
	 * @return R
	 */
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseRet handleBodyValidException(MethodArgumentNotValidException exception) {
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		log.warn("参数绑定异常,ex = {}", fieldErrors.get(0).getDefaultMessage());
		return ResponseRet.failed(fieldErrors.get(0).getDefaultMessage());
	}

	/**
	 * validation Exception (以form-data形式传参)
	 * @param exception
	 * @return R
	 */
	@ExceptionHandler({ BindException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseRet bindExceptionHandler(BindException exception) {
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		log.warn("参数绑定异常,ex = {}", fieldErrors.get(0).getDefaultMessage());
		return ResponseRet.failed(fieldErrors.get(0).getDefaultMessage());
	}

}
