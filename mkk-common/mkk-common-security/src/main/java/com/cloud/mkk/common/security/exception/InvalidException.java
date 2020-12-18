package com.cloud.mkk.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.cloud.mkk.common.security.component.MicroAuth2ExceptionSerializer;

/**
 * @author kuangjianyu
 * @date 2020-12-14
 */
@JsonSerialize(using = MicroAuth2ExceptionSerializer.class)
public class InvalidException extends MicroAuth2Exception {

	public InvalidException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "invalid_exception";
	}

	@Override
	public int getHttpErrorCode() {
		return 426;
	}

}
