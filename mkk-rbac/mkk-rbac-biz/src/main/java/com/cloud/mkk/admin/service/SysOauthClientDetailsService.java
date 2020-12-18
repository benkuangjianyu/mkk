package com.cloud.mkk.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.mkk.admin.api.entity.SysOauthClientDetails;

/**
 * 服务类
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
public interface SysOauthClientDetailsService extends IService<SysOauthClientDetails> {

	/**
	 * 通过ID删除客户端
	 * @param id
	 * @return
	 */
	Boolean removeClientDetailsById(String id);

	/**
	 * 根据客户端信息
	 * @param sysOauthClientDetails
	 * @return
	 */
	Boolean updateClientDetailsById(SysOauthClientDetails sysOauthClientDetails);

}
