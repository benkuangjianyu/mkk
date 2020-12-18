package com.cloud.mkk.codegen;

import com.cloud.mkk.common.datasource.annotation.EnableDynamicDataSource;
import com.cloud.mkk.common.security.annotation.EnableMicroFeignClients;
import com.cloud.mkk.common.security.annotation.EnableMicroResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 代码生成模块
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@EnableDynamicDataSource
@EnableMicroFeignClients
@SpringCloudApplication
@EnableMicroResourceServer
public class CodeGenApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeGenApplication.class, args);
	}

}
