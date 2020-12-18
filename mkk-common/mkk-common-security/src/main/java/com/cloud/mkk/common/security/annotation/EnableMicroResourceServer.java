package com.cloud.mkk.common.security.annotation;

import com.cloud.mkk.common.security.component.MicroResourceServerAutoConfiguration;
import com.cloud.mkk.common.security.component.MicroSecurityBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.lang.annotation.*;

/**
 * 资源服务注解
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Documented
@Inherited
@EnableResourceServer
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({ MicroResourceServerAutoConfiguration.class, MicroSecurityBeanDefinitionRegistrar.class })
public @interface EnableMicroResourceServer {

}
