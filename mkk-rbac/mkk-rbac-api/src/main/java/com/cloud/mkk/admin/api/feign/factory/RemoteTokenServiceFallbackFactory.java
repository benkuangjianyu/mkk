package com.cloud.mkk.admin.api.feign.factory;

import com.cloud.mkk.admin.api.feign.RemoteTokenService;
import com.cloud.mkk.admin.api.feign.fallback.RemoteTokenServiceFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Component
public class RemoteTokenServiceFallbackFactory implements FallbackFactory<RemoteTokenService> {

	@Override
	public RemoteTokenService create(Throwable throwable) {
		RemoteTokenServiceFallbackImpl remoteTokenServiceFallback = new RemoteTokenServiceFallbackImpl();
		remoteTokenServiceFallback.setCause(throwable);
		return remoteTokenServiceFallback;
	}

}
