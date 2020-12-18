package com.cloud.mkk.common.core.exception;

import lombok.NoArgsConstructor;

/**
 * 403 授权拒绝
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@NoArgsConstructor
public class MkkDeniedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MkkDeniedException(String message) {
		super(message);
	}

	public MkkDeniedException(Throwable cause) {
		super(cause);
	}

	public MkkDeniedException(String message, Throwable cause) {
		super(message, cause);
	}

	public MkkDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
