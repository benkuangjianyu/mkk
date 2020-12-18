package com.cloud.mkk.common.security.service;

import com.cloud.mkk.common.core.constant.CacheConstants;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * @author kuangjianyu
 * @date 2020-12-14
 */
public class MicroClientDetailsService extends JdbcClientDetailsService {

	public MicroClientDetailsService(DataSource dataSource) {
		super(dataSource);
	}

	/**
	 * 重写原生方法支持redis缓存
	 * @param clientId
	 * @return
	 */
	@Override
	@SneakyThrows
	@Cacheable(value = CacheConstants.CLIENT_DETAILS_KEY, key = "#clientId", unless = "#result == null")
	public ClientDetails loadClientByClientId(String clientId) {
		return super.loadClientByClientId(clientId);
	}

}
