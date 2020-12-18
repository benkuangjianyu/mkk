package com.cloud.mkk.config.api.feign.factory;

import com.cloud.mkk.config.api.feign.ParamFeign;
import com.cloud.mkk.config.api.feign.fallback.ParamFeignFallback;
import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

/**
 * 获取系统参数
 *
 * @author: KuangJianyu
 * @date: 2020年12月16日
 */
@Component
public class ParamFeignFallbackFactory  implements FallbackFactory<ParamFeign> {

	@Override
	public ParamFeign create(Throwable throwable) {
		ParamFeignFallback feignFallback = new ParamFeignFallback();
		feignFallback.setCause(throwable);
		return feignFallback;
	}
}
