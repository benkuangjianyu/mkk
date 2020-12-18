package com.cloud.mkk.common.core.exception;

/**
 * 验证码校验
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
public class ValidateCodeException extends RuntimeException {

	private static final long serialVersionUID = -7285211528095468156L;

	public ValidateCodeException() {
	}

	public ValidateCodeException(String msg) {
		super(msg);
	}

}
