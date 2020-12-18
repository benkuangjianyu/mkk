package com.cloud.mkk.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.cloud.mkk.common.security.component.MicroAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * @author kuangjianyu
 * @date 2020-12-14
 */
@JsonSerialize(using = MicroAuth2ExceptionSerializer.class)
public class MethodNotAllowed extends MicroAuth2Exception {

	public MethodNotAllowed(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "method_not_allowed";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.METHOD_NOT_ALLOWED.value();
	}

}
