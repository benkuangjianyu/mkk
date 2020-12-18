package com.cloud.mkk.admin.api.feign.fallback;

import com.cloud.mkk.admin.api.dto.UserInfo;
import com.cloud.mkk.admin.api.feign.RemoteUserService;
import com.cloud.mkk.common.core.util.ResponseRet;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Slf4j
@Component
public class RemoteUserServiceFallbackImpl implements RemoteUserService {

	@Setter
	private Throwable cause;

	/**
	 * 通过用户名查询用户、角色信息
	 * @param username 用户名
	 * @param from 内外标志
	 * @return R
	 */
	@Override
	public ResponseRet<UserInfo> info(String username, String from) {
		log.error("feign 查询用户信息失败:{}", username, cause);
		return null;
	}

	/**
	 * 通过社交账号查询用户、角色信息
	 * @param inStr appid@code
	 * @return
	 */
	@Override
	public ResponseRet<UserInfo> social(String inStr) {
		log.error("feign 查询用户信息失败:{}", inStr, cause);
		return null;
	}

}
