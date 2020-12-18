package com.cloud.mkk.common.core.constant;

/**
 * 通用常量
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
public interface CommonConstants {

	/**
	 * 删除
	 */
	String STATUS_DEL = "1";

	/**
	 * 正常
	 */
	String STATUS_NORMAL = "0";

	/**
	 * 锁定
	 */
	String STATUS_LOCK = "9";

	/**
	 * 菜单树根节点
	 */
	Integer MENU_TREE_ROOT_ID = -1;

	/**
	 * 菜单
	 */
	String MENU = "0";

	/**
	 * 编码
	 */
	String UTF8 = "UTF-8";

	/**
	 * JSON 资源
	 */
	String CONTENT_TYPE = "application/json; charset=utf-8";

	/**
	 * 前端工程名
	 */
	String FRONT_END_PROJECT = "micro-ui";

	/**
	 * 后端工程名
	 */
	String BACK_END_PROJECT = "mkk";

	/**
	 * 成功标记
	 */
	Integer SUCCESS = 0;

	/**
	 * 失败标记
	 */
	Integer FAIL = 1;

	/**
	 * 验证码前缀
	 */
	String DEFAULT_CODE_KEY = "DEFAULT_CODE_KEY_";

	/**
	 * 当前页
	 */
	String CURRENT = "current";

	/**
	 * size
	 */
	String SIZE = "size";

	/**
	 * Feign处理失败返回统一错误提示
	 */
	String FEIGN_ERROR_TEXT = "Feign调用失败 ";

	/**
	 * 请求开始时间拦截器Session Key
	 */
	String SPRINGMVC_INTERCEPTOR_REQUEST_START_TIME_STRING = "SpringMvc_Intereptor_Request_Start_Time";

}
