package com.cloud.mkk.config.api.feign.fallback;

import com.cloud.mkk.config.api.feign.ParamFeign;
import com.cloud.mkk.common.core.constant.CommonConstants;
import org.springframework.stereotype.Component;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 获取系统参数
 *
 * @author: KuangJianyu
 * @date: 2020年12月16日
 */
@Slf4j
@Component
public class ParamFeignFallback implements ParamFeign {
	@Setter
	private Throwable cause;

	@Override
	public String get(String key) {
		log.error(CommonConstants.FEIGN_ERROR_TEXT+"获取系统参数", cause);
		if(null!=cause) {
			System.out.println(cause.getMessage());
		}
		return null;
	}
}
