package com.cloud.mkk.config;

import com.cloud.mkk.common.security.annotation.EnableMicroFeignClients;
import com.cloud.mkk.common.security.annotation.EnableMicroResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 系统参数获取
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@EnableMicroResourceServer
@EnableMicroFeignClients
@SpringCloudApplication
public class ConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigApplication.class, args);
	}

}
