package com.cloud.mkk.gateway.config;

import com.cloud.mkk.gateway.handler.ImageCodeHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

/**
 * RouterFunctionConfiguration 路由配置信息
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class RouterFunctionConfiguration {

	private final ImageCodeHandler imageCodeHandler;

	@Bean
	public RouterFunction routerFunction() {
		return RouterFunctions.route(
				RequestPredicates.path("/code").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), imageCodeHandler);
	}

}
