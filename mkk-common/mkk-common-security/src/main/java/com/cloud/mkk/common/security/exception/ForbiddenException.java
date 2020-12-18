package com.cloud.mkk.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.cloud.mkk.common.security.component.MicroAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * @author kuangjianyu
 * @date 2020-12-14
 */
@JsonSerialize(using = MicroAuth2ExceptionSerializer.class)
public class ForbiddenException extends MicroAuth2Exception {

	public ForbiddenException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "access_denied";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.FORBIDDEN.value();
	}

}
