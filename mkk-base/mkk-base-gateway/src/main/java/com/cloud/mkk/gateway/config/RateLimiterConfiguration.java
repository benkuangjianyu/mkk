package com.cloud.mkk.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * RateLimiterConfiguration 路由限流配置
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Configuration
public class RateLimiterConfiguration {

	@Bean(value = "remoteAddrKeyResolver")
	public KeyResolver remoteAddrKeyResolver() {
		return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
	}

}
