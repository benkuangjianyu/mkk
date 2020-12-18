package com.cloud.mkk.common.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Configuration
public class RestTemplateConfiguration {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}