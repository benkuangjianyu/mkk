package com.cloud.mkk.common.mybatis.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * Druid 监控系统配置
 *
 * @author kuangjianyu
 * @date 2020-12-17
 */
@Configuration
public class DruidConfiguration {
	@Bean
    public ServletRegistrationBean<StatViewServlet> druidStatViewServle() {
        ServletRegistrationBean<StatViewServlet> servletRegistration = new ServletRegistrationBean<StatViewServlet>(new StatViewServlet(), "/druid/*");
        //白名单：
        servletRegistration.addInitParameter("allow", "127.0.0.1");
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        //servletRegistrationBean.addInitParameter("deny", "192.168.1.73");
        //登录查看信息的账号密码.
        servletRegistration.addInitParameter("loginUsername", "admin");
        servletRegistration.addInitParameter("loginPassword", "123456");
        //是否能够重置数据.
        servletRegistration.addInitParameter("resetEnable", "false");
        return servletRegistration;
    }
	/**
     * 注册一个：filterRegistrationBean
     * @return
     */
    @Bean
    public FilterRegistrationBean<WebStatFilter> druidStatFilter() {
        FilterRegistrationBean<WebStatFilter> filterRegistration = new FilterRegistrationBean<WebStatFilter>(new WebStatFilter());
        //添加过滤规则.
        filterRegistration.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        filterRegistration.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistration;
    }
}
