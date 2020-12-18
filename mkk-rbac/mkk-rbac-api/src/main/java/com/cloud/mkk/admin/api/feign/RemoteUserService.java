package com.cloud.mkk.admin.api.feign;

import com.cloud.mkk.admin.api.dto.UserInfo;
import com.cloud.mkk.admin.api.feign.factory.RemoteUserServiceFallbackFactory;
import com.cloud.mkk.common.core.constant.SecurityConstants;
import com.cloud.mkk.common.core.constant.ServiceNameConstants;
import com.cloud.mkk.common.core.util.ResponseRet;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author kuangjianyu
 * @date 2020-12-14
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.UMPS_SERVICE,
		fallbackFactory = RemoteUserServiceFallbackFactory.class)
public interface RemoteUserService {

	/**
	 * 通过用户名查询用户、角色信息
	 * @param username 用户名
	 * @param from 调用标志
	 * @return R
	 */
	@GetMapping("/user/info/{username}")
    ResponseRet<UserInfo> info(@PathVariable("username") String username, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 通过社交账号查询用户、角色信息
	 * @param inStr appid@code
	 * @return
	 */
	@GetMapping("/social/info/{inStr}")
    ResponseRet<UserInfo> social(@PathVariable("inStr") String inStr);

}
