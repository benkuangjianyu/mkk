package com.cloud.mkk.common.core.config;

import cn.hutool.core.date.DatePattern;
import com.cloud.mkk.common.core.jackson.MkkJavaTimeModule;
import com.cloud.mkk.common.core.mvc.RequestParameterFilter;
import com.cloud.mkk.common.core.mvc.SpringMvcInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.swing.*;
import java.time.format.DateTimeFormatter;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.SERVLET;

/**
 * 注入自自定义SQL 过滤
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Configuration
@ConditionalOnWebApplication(type = SERVLET)
public class WebMvcConfiguration implements WebMvcConfigurer {

	/**
	 * 增加GET请求参数中时间类型转换 {@link MkkJavaTimeModule}
	 * <ul>
	 * <li>HH:mm:ss -> LocalTime</li>
	 * <li>yyyy-MM-dd -> LocalDate</li>
	 * <li>yyyy-MM-dd HH:mm:ss -> LocalDateTime</li>
	 * </ul>
	 * @param registry
	 */
	@Override
	public void addFormatters(FormatterRegistry registry) {
		DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
		registrar.setTimeFormatter(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN));
		registrar.setDateFormatter(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN));
		registrar.setDateTimeFormatter(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN));
		registrar.registerFormatters(registry);
	}

	/**
	 * 请求参数过滤器
	 * @return FilterRegistrationBean
	 @Bean("requestParameterFilter")
	 */
	public FilterRegistrationBean<RequestParameterFilter> requestParameterFilter() {
		FilterRegistrationBean<RequestParameterFilter> registration = new FilterRegistrationBean<RequestParameterFilter>();
		registration.setFilter(new RequestParameterFilter());
		registration.addUrlPatterns("/*");
		registration.addInitParameter("paramName", "paramValue");
		registration.setName("requestParameterFilter");
		registration.setOrder(0);
		return registration;
	}

	/**
	 * 注册自定义拦截器，添加拦截路径和排除拦截路径
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SpringMvcInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("assets/")
				.excludePathPatterns("druid/")
				.excludePathPatterns("dict/type");
	}
}
