package com.cloud.mkk.common.datasource.annotation;

import com.cloud.mkk.common.datasource.DynamicDataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启动态数据源
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(DynamicDataSourceAutoConfiguration.class)
public @interface EnableDynamicDataSource {

}
