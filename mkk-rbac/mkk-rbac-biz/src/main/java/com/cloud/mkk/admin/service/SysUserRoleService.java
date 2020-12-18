package com.cloud.mkk.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.mkk.admin.api.entity.SysUserRole;

/**
 * 用户角色表 服务类
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
public interface SysUserRoleService extends IService<SysUserRole> {

	/**
	 * 根据用户Id删除该用户的角色关系
	 * @param userId 用户ID
	 * @return boolean
	 * @author 寻欢·李
	 * @date 2017年12月7日 16:31:38
	 */
	Boolean removeRoleByUserId(Integer userId);

}
