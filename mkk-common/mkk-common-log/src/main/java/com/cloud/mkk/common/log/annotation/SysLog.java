package com.cloud.mkk.common.log.annotation;

import java.lang.annotation.*;

/**
 * 操作日志注解
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

	/**
	 * 描述
	 * @return {String}
	 */
	String value();

}
