package com.cloud.mkk.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.mkk.admin.api.entity.SysRole;
import com.cloud.mkk.admin.api.vo.RoleVo;
import com.cloud.mkk.admin.service.SysRoleMenuService;
import com.cloud.mkk.admin.service.SysRoleService;
import com.cloud.mkk.common.core.util.ResponseRet;
import com.cloud.mkk.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 角色管理模块
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
@Api(value = "role", tags = "角色管理模块")
public class RoleController {

	private final SysRoleService sysRoleService;

	private final SysRoleMenuService sysRoleMenuService;

	/**
	 * 通过ID查询角色信息
	 * @param id ID
	 * @return 角色信息
	 */
	@GetMapping("/{id}")
	public ResponseRet getById(@PathVariable Integer id) {
		return ResponseRet.ok(sysRoleService.getById(id));
	}

	/**
	 * 添加角色
	 * @param sysRole 角色信息
	 * @return success、false
	 */
	@SysLog("添加角色")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_role_add')")
	public ResponseRet save(@Valid @RequestBody SysRole sysRole) {
		return ResponseRet.ok(sysRoleService.save(sysRole));
	}

	/**
	 * 修改角色
	 * @param sysRole 角色信息
	 * @return success/false
	 */
	@SysLog("修改角色")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys_role_edit')")
	public ResponseRet update(@Valid @RequestBody SysRole sysRole) {
		return ResponseRet.ok(sysRoleService.updateById(sysRole));
	}

	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	@SysLog("删除角色")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_role_del')")
	public ResponseRet removeById(@PathVariable Integer id) {
		return ResponseRet.ok(sysRoleService.removeRoleById(id));
	}

	/**
	 * 获取角色列表
	 * @return 角色列表
	 */
	@GetMapping("/list")
	public ResponseRet listRoles() {
		return ResponseRet.ok(sysRoleService.list(Wrappers.emptyWrapper()));
	}

	/**
	 * 分页查询角色信息
	 * @param page 分页对象
	 * @return 分页对象
	 */
	@GetMapping("/page")
	public ResponseRet getRolePage(Page page) {
		return ResponseRet.ok(sysRoleService.page(page, Wrappers.emptyWrapper()));
	}

	/**
	 * 更新角色菜单
	 * @param roleVo 角色对象
	 * @return success、false
	 */
	@SysLog("更新角色菜单")
	@PutMapping("/menu")
	@PreAuthorize("@pms.hasPermission('sys_role_perm')")
	public ResponseRet saveRoleMenus(@RequestBody RoleVo roleVo) {
		SysRole sysRole = sysRoleService.getById(roleVo.getRoleId());
		return ResponseRet.ok(sysRoleMenuService.saveRoleMenus(sysRole.getRoleCode(), roleVo.getRoleId(), roleVo.getMenuIds()));
	}

}
