package com.cloud.mkk.common.log.init;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * 通过环境变量的形式注入 logging.file 自动维护 Spring Boot Admin Logger Viewer
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
public class ApplicationLoggerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
		ConfigurableEnvironment environment = applicationContext.getEnvironment();

		String appName = environment.getProperty("spring.application.name");

		String logBase = environment.getProperty("LOGGING_PATH", "logs");
		// spring boot admin 直接加载日志
		System.setProperty("logging.file.name", String.format("%s/%s/debug.log", logBase, appName));
	}

}
