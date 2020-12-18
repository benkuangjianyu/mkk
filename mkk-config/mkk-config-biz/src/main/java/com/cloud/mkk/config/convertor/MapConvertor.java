package com.cloud.mkk.config.convertor;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 系统参数Map
 *
 * @author: KuangJianyu
 * @date: 2020年12月16日
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "param")
@EnableConfigurationProperties(MapConvertor.class)
public class MapConvertor {
	private Map<String, String> global = new HashMap<>();
	private Map<String, String> ebidding = new HashMap<>();
}