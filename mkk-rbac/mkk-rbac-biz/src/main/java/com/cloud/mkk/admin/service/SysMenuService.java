package com.cloud.mkk.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.mkk.admin.api.dto.MenuTree;
import com.cloud.mkk.admin.api.entity.SysMenu;
import com.cloud.mkk.admin.api.vo.MenuVO;
import com.cloud.mkk.common.core.util.ResponseRet;

import java.util.List;
import java.util.Set;

/**
 * 菜单权限表 服务类
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
public interface SysMenuService extends IService<SysMenu> {

	/**
	 * 通过角色编号查询URL 权限
	 * @param roleId 角色ID
	 * @return 菜单列表
	 */
	List<MenuVO> findMenuByRoleId(Integer roleId);

	/**
	 * 级联删除菜单
	 * @param id 菜单ID
	 * @return true成功,false失败
	 */
	ResponseRet removeMenuById(Integer id);

	/**
	 * 更新菜单信息
	 * @param sysMenu 菜单信息
	 * @return 成功、失败
	 */
	Boolean updateMenuById(SysMenu sysMenu);

	/**
	 * 构建树
	 * @param lazy 是否是懒加载
	 * @param parentId 父节点ID
	 * @return
	 */
	List<MenuTree> treeMenu(boolean lazy, Integer parentId);

	/**
	 * 查询菜单
	 * @param menuSet
	 * @param parentId
	 * @return
	 */
	List<MenuTree> filterMenu(Set<MenuVO> menuSet, Integer parentId);

}
