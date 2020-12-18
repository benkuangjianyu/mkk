package com.cloud.mkk.admin.api.feign;

import com.cloud.mkk.admin.api.entity.SysLog;
import com.cloud.mkk.admin.api.feign.factory.RemoteLogServiceFallbackFactory;
import com.cloud.mkk.common.core.constant.SecurityConstants;
import com.cloud.mkk.common.core.constant.ServiceNameConstants;
import com.cloud.mkk.common.core.util.ResponseRet;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author kuangjianyu
 * @date 2020-12-14
 */
@FeignClient(contextId = "remoteLogService", value = ServiceNameConstants.UMPS_SERVICE,
		fallbackFactory = RemoteLogServiceFallbackFactory.class)
public interface RemoteLogService {

	/**
	 * 保存日志
	 * @param sysLog 日志实体
	 * @param from 内部调用标志
	 * @return succes、false
	 */
	@PostMapping("/log")
    ResponseRet<Boolean> saveLog(@RequestBody SysLog sysLog, @RequestHeader(SecurityConstants.FROM) String from);

}
