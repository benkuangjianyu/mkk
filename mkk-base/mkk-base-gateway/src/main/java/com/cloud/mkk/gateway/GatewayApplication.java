package com.cloud.mkk.gateway;

import com.cloud.mkk.common.swagger.annotation.EnableMicroSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * MkkGatewayApplication 网关应用
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@EnableMicroSwagger2
@SpringCloudApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}