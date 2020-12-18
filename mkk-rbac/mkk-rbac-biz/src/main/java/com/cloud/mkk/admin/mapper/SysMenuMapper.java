package com.cloud.mkk.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.mkk.admin.api.entity.SysMenu;
import com.cloud.mkk.admin.api.vo.MenuVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单权限表 Mapper 接口
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

	/**
	 * 通过角色编号查询菜单
	 * @param roleId 角色ID
	 * @return
	 */
	List<MenuVO> listMenusByRoleId(Integer roleId);

	/**
	 * 通过角色ID查询权限
	 * @param roleIds Ids
	 * @return
	 */
	List<String> listPermissionsByRoleIds(String roleIds);

}
