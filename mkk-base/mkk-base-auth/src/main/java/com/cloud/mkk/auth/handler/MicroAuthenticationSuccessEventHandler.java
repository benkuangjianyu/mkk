package com.cloud.mkk.auth.handler;

import com.cloud.mkk.common.security.handler.AbstractAuthenticationSuccessEventHandler;
import com.cloud.mkk.common.security.service.MicroUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * MicroAuthenticationSuccessEventHandler
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Slf4j
@Component
public class MicroAuthenticationSuccessEventHandler extends AbstractAuthenticationSuccessEventHandler {

	/**
	 * 处理登录成功方法
	 * <p>
	 * 获取到登录的authentication 对象
	 * @param authentication 登录对象
	 */
	@Override
	public void handle(Authentication authentication) {
		MicroUser microUser = (MicroUser)authentication.getPrincipal();
		log.info("用户：{} 登录成功", microUser.getUsername());
	}
}
