package com.cloud.mkk.common.sentinel;

import com.alibaba.cloud.sentinel.feign.SentinelFeignAutoConfiguration;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import com.cloud.mkk.common.sentinel.feign.MicroSentinelFeign;
import com.cloud.mkk.common.sentinel.handle.MicroUrlBlockHandler;
import com.cloud.mkk.common.sentinel.parser.MicroHeaderRequestOriginParser;
import feign.Feign;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * sentinel 配置
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore(SentinelFeignAutoConfiguration.class)
public class SentinelAutoConfiguration {

	@Bean
	@Scope("prototype")
	@ConditionalOnMissingBean
	@ConditionalOnProperty(name = "feign.sentinel.enabled")
	public Feign.Builder feignSentinelBuilder() {
		return MicroSentinelFeign.builder();
	}

	@Bean
	@ConditionalOnMissingBean
	public BlockExceptionHandler blockExceptionHandler() {
		return new MicroUrlBlockHandler();
	}

	@Bean
	@ConditionalOnMissingBean
	public RequestOriginParser requestOriginParser() {
		return new MicroHeaderRequestOriginParser();
	}

}
