package com.cloud.mkk.common.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.cloud.mkk.common.mybatis.interceptor.MybatisResultInterceptor;
import com.cloud.mkk.common.mybatis.resolver.SqlFilterArgumentResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;
import java.util.Properties;

/**
 * mybatis plus 统一配置
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
public class MybatisAutoConfiguration implements WebMvcConfigurer {
	@Resource
	private MybatisResultInterceptor mybatisResultInterceptor;

	/**
	 * 从配置文件获取属性构造datasource，注意前缀，这里用的是druid，根据自己情况配置,
	 * 原生datasource前缀取"spring.datasource"
	 * @return DataSource
	 */
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource druidDataSource() {
		DruidDataSource datasource = new DruidDataSource();
//		configuration
//		datasource.setInitialSize(initialSize);
//		datasource.setMinIdle(minIdle);
//		datasource.setMaxActive(maxActive);
//		datasource.setMaxWait(maxWait);
//		datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
//		datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
//		datasource.setValidationQuery(validationQuery);
//		datasource.setTestWhileIdle(testWhileIdle);
//		datasource.setTestOnBorrow(testOnBorrow);
//		datasource.setTestOnReturn(testOnReturn);
//		datasource.setPoolPreparedStatements(poolPreparedStatements);
//		datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
//		datasource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
//		try {
//			datasource.setFilters(filters);
//		} catch (SQLException e) {
//			System.err.println("druid configuration initialization filter: "+ e);
//		}
//		datasource.setConnectionProperties(connectionProperties);
		log.info("初始化数据源配置OK......");
		return datasource;
	}

	/**
	 * SQL 过滤器避免SQL 注入
	 * @param argumentResolvers
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new SqlFilterArgumentResolver());
	}

	/**
	 * 分页插件, 对于单一数据库类型来说,都建议配置该值,避免每次分页都去抓取数据库类型
	 */
	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
		return interceptor;
	}

	/**
	 * 设置SQL执行拦截器
	 */
	@Bean
	public String myInterceptor() {
		Properties properties = new Properties();
		this.mybatisResultInterceptor.setProperties(properties);
		return "interceptor";
	}
}
