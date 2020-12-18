package com.cloud.mkk.auth;

import com.cloud.mkk.common.security.annotation.EnableMicroFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * AuthApplication 认证授权中心
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@SpringCloudApplication
@EnableMicroFeignClients
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

}
