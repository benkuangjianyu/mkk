package com.cloud.mkk.admin;

import com.cloud.mkk.common.security.annotation.EnableMicroFeignClients;
import com.cloud.mkk.common.security.annotation.EnableMicroResourceServer;
import com.cloud.mkk.common.swagger.annotation.EnableMicroSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 用户统一管理系统
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@EnableMicroSwagger2
@EnableMicroResourceServer
@EnableMicroFeignClients
@SpringCloudApplication
public class AdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}

}
