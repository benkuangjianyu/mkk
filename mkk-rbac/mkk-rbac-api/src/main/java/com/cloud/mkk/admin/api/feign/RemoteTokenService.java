package com.cloud.mkk.admin.api.feign;

import com.cloud.mkk.admin.api.feign.factory.RemoteTokenServiceFallbackFactory;
import com.cloud.mkk.common.core.constant.SecurityConstants;
import com.cloud.mkk.common.core.constant.ServiceNameConstants;
import com.cloud.mkk.common.core.util.ResponseRet;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author kuangjianyu
 * @date 2020-12-14
 */
@FeignClient(contextId = "remoteTokenService", value = ServiceNameConstants.AUTH_SERVICE,
		fallbackFactory = RemoteTokenServiceFallbackFactory.class)
public interface RemoteTokenService {

	/**
	 * 分页查询token 信息
	 * @param params 分页参数
	 * @param from 内部调用标志
	 * @return page
	 */
	@PostMapping("/token/page")
    ResponseRet getTokenPage(@RequestBody Map<String, Object> params, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 删除token
	 * @param token token
	 * @param from 调用标志
	 * @return
	 */
	@DeleteMapping("/token/{token}")
    ResponseRet<Boolean> removeToken(@PathVariable("token") String token, @RequestHeader(SecurityConstants.FROM) String from);

}
