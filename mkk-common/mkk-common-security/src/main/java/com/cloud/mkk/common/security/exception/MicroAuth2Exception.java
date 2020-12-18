package com.cloud.mkk.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.cloud.mkk.common.security.component.MicroAuth2ExceptionSerializer;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * 自定义OAuth2Exception
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@JsonSerialize(using = MicroAuth2ExceptionSerializer.class)
public class MicroAuth2Exception extends OAuth2Exception {

	@Getter
	private String errorCode;

	public MicroAuth2Exception(String msg) {
		super(msg);
	}

	public MicroAuth2Exception(String msg, String errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}

}
