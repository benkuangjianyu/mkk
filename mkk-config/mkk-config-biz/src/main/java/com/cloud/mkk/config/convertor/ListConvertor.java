package com.cloud.mkk.config.convertor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 系统参数List
 *
 * @author: KuangJianyu
 * @date: 2020年12月16日
 */
@Configuration
@ConfigurationProperties(prefix = "param")
@EnableConfigurationProperties(ListConvertor.class)
public class ListConvertor {
	private List<String> global = new ArrayList<>();
	private List<String> ebidding = new ArrayList<>();
	
	public List<String> getGlobal() {
		return global;
	}
	public void setGlobal(List<String> global) {
		this.global = global;
	}
	public List<String> getEbidding() {
		return ebidding;
	}
	public void setEbidding(List<String> ebidding) {
		this.ebidding = ebidding;
	}
}
