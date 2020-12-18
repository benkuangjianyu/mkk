package com.cloud.mkk.common.core.util;

import com.cloud.mkk.common.core.constant.CommonConstants;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 响应信息主体
 *
 * @param <T>
 * @author kuangjianyu
 * @date 2020-12-14
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ResponseRet<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private int code;

	@Getter
	@Setter
	private String msg;

	@Getter
	@Setter
	private T data;

	public static <T> ResponseRet<T> ok() {
		return restResult(null, CommonConstants.SUCCESS, null);
	}

	public static <T> ResponseRet<T> ok(T data) {
		return restResult(data, CommonConstants.SUCCESS, null);
	}

	public static <T> ResponseRet<T> ok(T data, String msg) {
		return restResult(data, CommonConstants.SUCCESS, msg);
	}

	public static <T> ResponseRet<T> failed() {
		return restResult(null, CommonConstants.FAIL, null);
	}

	public static <T> ResponseRet<T> failed(String msg) {
		return restResult(null, CommonConstants.FAIL, msg);
	}

	public static <T> ResponseRet<T> failed(T data) {
		return restResult(data, CommonConstants.FAIL, null);
	}

	public static <T> ResponseRet<T> failed(T data, String msg) {
		return restResult(data, CommonConstants.FAIL, msg);
	}

	private static <T> ResponseRet<T> restResult(T data, int code, String msg) {
		ResponseRet<T> apiResult = new ResponseRet<>();
		apiResult.setCode(code);
		apiResult.setData(data);
		apiResult.setMsg(msg);
		return apiResult;
	}

}
