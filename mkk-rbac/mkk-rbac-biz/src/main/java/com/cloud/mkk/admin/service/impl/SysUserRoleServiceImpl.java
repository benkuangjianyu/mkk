package com.cloud.mkk.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.mkk.admin.api.entity.SysUserRole;
import com.cloud.mkk.admin.service.SysUserRoleService;
import com.cloud.mkk.admin.mapper.SysUserRoleMapper;
import org.springframework.stereotype.Service;

/**
 * 用户角色表 服务实现类
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

	/**
	 * 根据用户Id删除该用户的角色关系
	 * @param userId 用户ID
	 * @return boolean
	 * @author 寻欢·李
	 * @date 2017年12月7日 16:31:38
	 */
	@Override
	public Boolean removeRoleByUserId(Integer userId) {
		return baseMapper.deleteByUserId(userId);
	}

}
