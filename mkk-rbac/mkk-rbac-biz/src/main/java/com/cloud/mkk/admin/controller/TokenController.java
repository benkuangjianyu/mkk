package com.cloud.mkk.admin.controller;

import com.cloud.mkk.admin.api.feign.RemoteTokenService;
import com.cloud.mkk.common.core.constant.SecurityConstants;
import com.cloud.mkk.common.core.util.ResponseRet;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 令牌管理模块
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
@Api(value = "token", tags = "令牌管理模块")
public class TokenController {

	private final RemoteTokenService remoteTokenService;

	/**
	 * 分页token 信息
	 * @param params 参数集
	 * @return token集合
	 */
	@GetMapping("/page")
	public ResponseRet token(@RequestParam Map<String, Object> params) {
		return remoteTokenService.getTokenPage(params, SecurityConstants.FROM_IN);
	}

	/**
	 * 删除
	 * @param id ID
	 * @return success/false
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_token_del')")
	public ResponseRet<Boolean> delete(@PathVariable String id) {
		return remoteTokenService.removeToken(id, SecurityConstants.FROM_IN);
	}

}
