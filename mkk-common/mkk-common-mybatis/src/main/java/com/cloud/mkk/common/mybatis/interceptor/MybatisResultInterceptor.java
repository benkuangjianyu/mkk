package com.cloud.mkk.common.mybatis.interceptor;

import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;

/**
 * 自定义mybatis拦截器，格式化SQL输出
 * 只对查询和更新语句做了格式化，其它语句需要手动添加
 *
 * @author kuangjianyu
 * @date 2020-12-17
 **/
@Slf4j
@Intercepts({
		@org.apache.ibatis.plugin.Signature(type = org.apache.ibatis.executor.Executor.class, method = "update", args = {
				MappedStatement.class, Object.class }),
		@org.apache.ibatis.plugin.Signature(type = org.apache.ibatis.executor.Executor.class, method = "query", args = {
				MappedStatement.class, Object.class, org.apache.ibatis.session.RowBounds.class,
				org.apache.ibatis.session.ResultHandler.class }) })

@Component
public class MybatisResultInterceptor implements Interceptor {
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		String sql = "";
		try {
			MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
			Object parameter = null;
			if (invocation.getArgs().length > 1) {
				parameter = invocation.getArgs()[1];
			}
			String sqlId = mappedStatement.getId();
			if(sqlId.indexOf(".")>-1) {
				String firsta = sqlId.substring(0, sqlId.lastIndexOf("."));
				String firstb = sqlId.substring(sqlId.lastIndexOf("."));
				if(firsta.indexOf(".")>-1) {
					String secondb = firsta.substring(firsta.lastIndexOf(".")+1);
					sqlId = secondb+firstb;
				}
			}
			BoundSql boundSql = mappedStatement.getBoundSql(parameter);
			Configuration configuration = mappedStatement.getConfiguration();
			sql = getSql(configuration, boundSql, sqlId);
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		long startTimeMillis = System.currentTimeMillis();
		Object proceedReslut = invocation.proceed();
		long endTimeMillis = System.currentTimeMillis();
		log.info("{}[SQL执行时间：{}毫秒]", sql, (endTimeMillis - startTimeMillis));
		return proceedReslut;
	}

	public static String getSql(Configuration configuration, BoundSql boundSql, String sqlId) {
		String sql = showSql(configuration, boundSql);
		StringBuilder str = new StringBuilder(100);
//		str.append("{"+sqlId+"}");
		str.append(sql);
		return str.toString().replaceAll("`", "");
	}

	private static String getParameterValue(Object obj) {
		String value = null;
		if ((obj instanceof String)) {
			value = "'" + obj.toString() + "'";
		} else if ((obj instanceof Date)) {
			DateFormat formatter = DateFormat.getDateTimeInstance(2, 2, Locale.CHINA);
			value = "'" + formatter.format(new Date()) + "'";
		} else if (obj != null) {
			value = obj.toString();
		} else {
			value = "";
		}
		return value;
	}

	public static String showSql(Configuration configuration, BoundSql boundSql) {
		Object parameterObject = boundSql.getParameterObject();
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
		MetaObject metaObject;
		if ((CollectionUtil.isNotEmpty(parameterMappings)) && (parameterObject != null)) {
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
				sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(parameterObject)));
			} else {
				metaObject = configuration.newMetaObject(parameterObject);
				for (ParameterMapping parameterMapping : parameterMappings) {
					String propertyName = parameterMapping.getProperty();
					if (metaObject.hasGetter(propertyName)) {
						Object obj = metaObject.getValue(propertyName);
						sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						Object obj = boundSql.getAdditionalParameter(propertyName);
						sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
					} else {
						sql = sql.replaceFirst("\\?", "缺失");
					}
				}
			}
		}
		return sql;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
	}
}