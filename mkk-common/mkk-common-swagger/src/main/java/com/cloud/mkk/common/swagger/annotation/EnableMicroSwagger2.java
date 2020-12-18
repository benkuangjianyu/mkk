package com.cloud.mkk.common.swagger.annotation;

import com.cloud.mkk.common.swagger.config.GatewaySwaggerAutoConfiguration;
import com.cloud.mkk.common.swagger.config.SwaggerAutoConfiguration;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.annotation.*;

/**
 * 开启 swagger
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableSwagger2
@Import({ SwaggerAutoConfiguration.class, GatewaySwaggerAutoConfiguration.class })
public @interface EnableMicroSwagger2 {

}
