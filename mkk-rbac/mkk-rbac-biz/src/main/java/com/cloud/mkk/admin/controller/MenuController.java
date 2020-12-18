package com.cloud.mkk.admin.controller;

import com.cloud.mkk.admin.api.entity.SysMenu;
import com.cloud.mkk.admin.api.vo.MenuVO;
import com.cloud.mkk.admin.service.SysMenuService;
import com.cloud.mkk.common.core.util.ResponseRet;
import com.cloud.mkk.common.log.annotation.SysLog;
import com.cloud.mkk.common.security.util.SecurityUtils;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 菜单管理模块
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
@Api(value = "menu", tags = "菜单管理模块")
public class MenuController {

	private final SysMenuService sysMenuService;

	/**
	 * 返回当前用户的树形菜单集合
	 * @param parentId 父节点ID
	 * @return 当前用户的树形菜单
	 */
	@GetMapping
	public ResponseRet getUserMenu(Integer parentId) {

		// 获取符合条件的菜单
		Set<MenuVO> all = new HashSet<>();
		SecurityUtils.getRoles().forEach(roleId -> all.addAll(sysMenuService.findMenuByRoleId(roleId)));
		return ResponseRet.ok(sysMenuService.filterMenu(all, parentId));
	}

	/**
	 * 返回树形菜单集合
	 * @param lazy 是否是懒加载
	 * @param parentId 父节点ID
	 * @return 树形菜单
	 */
	@GetMapping(value = "/tree")
	public ResponseRet getTree(boolean lazy, Integer parentId) {
		return ResponseRet.ok(sysMenuService.treeMenu(lazy, parentId));
	}

	/**
	 * 返回角色的菜单集合
	 * @param roleId 角色ID
	 * @return 属性集合
	 */
	@GetMapping("/tree/{roleId}")
	public ResponseRet getRoleTree(@PathVariable Integer roleId) {
		return ResponseRet.ok(
				sysMenuService.findMenuByRoleId(roleId).stream().map(MenuVO::getMenuId).collect(Collectors.toList()));
	}

	/**
	 * 通过ID查询菜单的详细信息
	 * @param id 菜单ID
	 * @return 菜单详细信息
	 */
	@GetMapping("/{id}")
	public ResponseRet getById(@PathVariable Integer id) {
		return ResponseRet.ok(sysMenuService.getById(id));
	}

	/**
	 * 新增菜单
	 * @param sysMenu 菜单信息
	 * @return 含ID 菜单信息
	 */
	@SysLog("新增菜单")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_menu_add')")
	public ResponseRet save(@Valid @RequestBody SysMenu sysMenu) {
		sysMenuService.save(sysMenu);
		return ResponseRet.ok(sysMenu);
	}

	/**
	 * 删除菜单
	 * @param id 菜单ID
	 * @return success/false
	 */
	@SysLog("删除菜单")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_menu_del')")
	public ResponseRet removeById(@PathVariable Integer id) {
		return sysMenuService.removeMenuById(id);
	}

	/**
	 * 更新菜单
	 * @param sysMenu
	 * @return
	 */
	@SysLog("更新菜单")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys_menu_edit')")
	public ResponseRet update(@Valid @RequestBody SysMenu sysMenu) {
		return ResponseRet.ok(sysMenuService.updateMenuById(sysMenu));
	}

}
