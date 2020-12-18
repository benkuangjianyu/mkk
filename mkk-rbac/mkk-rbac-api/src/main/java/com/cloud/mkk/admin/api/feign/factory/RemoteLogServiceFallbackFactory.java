package com.cloud.mkk.admin.api.feign.factory;

import com.cloud.mkk.admin.api.feign.RemoteLogService;
import com.cloud.mkk.admin.api.feign.fallback.RemoteLogServiceFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Component
public class RemoteLogServiceFallbackFactory implements FallbackFactory<RemoteLogService> {

	@Override
	public RemoteLogService create(Throwable throwable) {
		RemoteLogServiceFallbackImpl remoteLogServiceFallback = new RemoteLogServiceFallbackImpl();
		remoteLogServiceFallback.setCause(throwable);
		return remoteLogServiceFallback;
	}

}
