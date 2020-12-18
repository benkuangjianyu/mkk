package com.cloud.mkk.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.mkk.admin.api.entity.SysRole;

import java.util.List;

/**
 * 服务类
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
public interface SysRoleService extends IService<SysRole> {

	/**
	 * 通过用户ID，查询角色信息
	 * @param userId
	 * @return
	 */
	List<SysRole> findRolesByUserId(Integer userId);

	/**
	 * 通过角色ID，删除角色
	 * @param id
	 * @return
	 */
	Boolean removeRoleById(Integer id);

}
