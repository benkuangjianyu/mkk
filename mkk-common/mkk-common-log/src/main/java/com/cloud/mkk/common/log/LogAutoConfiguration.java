package com.cloud.mkk.common.log;

import com.cloud.mkk.admin.api.feign.RemoteLogService;
import com.cloud.mkk.common.log.aspect.SysLogAspect;
import com.cloud.mkk.common.log.event.SysLogListener;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 日志自动配置
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@EnableAsync
@RequiredArgsConstructor
@ConditionalOnWebApplication
@Configuration(proxyBeanMethods = false)
public class LogAutoConfiguration {

	private final RemoteLogService remoteLogService;

	@Bean
	public SysLogListener sysLogListener() {
		return new SysLogListener(remoteLogService);
	}

	@Bean
	public SysLogAspect sysLogAspect() {
		return new SysLogAspect();
	}

}
