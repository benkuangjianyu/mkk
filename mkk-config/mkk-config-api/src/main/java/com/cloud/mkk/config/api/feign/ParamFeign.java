package com.cloud.mkk.config.api.feign;

import com.cloud.mkk.common.core.constant.ServiceNameConstants;
import com.cloud.mkk.config.api.feign.factory.ParamFeignFallbackFactory;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 获取系统参数
 *
 * @author: KuangJianyu
 * @date: 2020年12月16日
 */
@FeignClient(contextId = "paramFeign", value = ServiceNameConstants.CONFIG_SERVICE, fallbackFactory = ParamFeignFallbackFactory.class)
public interface ParamFeign {
    /**
     * 获取系统参数
     * @param key
     * @return String
     */
    @ApiOperation(value = "获取系统参数", notes = "获取系统参数")
    @PutMapping("/get")
    public String get(@RequestParam("key") String key);
}

