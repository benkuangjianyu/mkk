package com.cloud.mkk.common.core.constant.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 系统登录类型
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Getter
@RequiredArgsConstructor
public enum LoginTypeEnum {

	/**
	 * 账号密码登录
	 */
	PWD("PWD", "账号密码登录"),

	/**
	 * QQ登录
	 */
	QQ("QQ", "QQ登录"),

	/**
	 * 微信登录
	 */
	WECHAT("WX", "微信登录");

	/**
	 * 类型
	 */
	private final String type;

	/**
	 * 描述
	 */
	private final String description;

}
