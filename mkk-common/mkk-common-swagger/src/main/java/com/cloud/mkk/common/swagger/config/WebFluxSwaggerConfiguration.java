package com.cloud.mkk.common.swagger.config;

import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * webflux 网关 swagger 资源路径配置
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
public class WebFluxSwaggerConfiguration implements WebFluxConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/swagger-ui/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
				.resourceChain(false);
	}

}