package com.cloud.mkk.config.feign;

import java.util.Map;

import com.cloud.mkk.common.security.annotation.Inner;
import com.cloud.mkk.config.api.feign.ParamFeign;
import com.cloud.mkk.config.convertor.MapConvertor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.hutool.core.collection.CollectionUtil;

/**
 * 获取系统参数值
 *
 * @author: KuangJianyu
 * @date: 2020年12月16日
 */
@RestController
@RequiredArgsConstructor
public class IParamFeign implements ParamFeign {
	private final MapConvertor mapConvertor;

	@Override
	@Inner(value = false)
	@PutMapping("/get")
	public String get(@RequestParam("key") String key) {
		Map<String, String> globalMap = mapConvertor.getGlobal();
		if(CollectionUtil.isEmpty(globalMap)) {
			return "";
		}
		return globalMap.get(key);
	}
}
