package com.cloud.mkk.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.mkk.admin.api.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Mapper 接口
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

	/**
	 * 通过用户ID，查询角色信息
	 * @param userId
	 * @return
	 */
	List<SysRole> listRolesByUserId(Integer userId);

}
