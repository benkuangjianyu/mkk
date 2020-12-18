package com.cloud.mkk.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.mkk.admin.api.dto.UserDTO;
import com.cloud.mkk.admin.api.entity.SysUser;
import com.cloud.mkk.admin.service.SysUserService;
import com.cloud.mkk.common.core.util.ResponseRet;
import com.cloud.mkk.common.log.annotation.SysLog;
import com.cloud.mkk.common.security.annotation.Inner;
import com.cloud.mkk.common.security.util.SecurityUtils;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户管理模块
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Api(value = "user", tags = "用户管理模块")
public class UserController {

	private final SysUserService userService;

	/**
	 * 获取当前用户全部信息
	 * @return 用户信息
	 */
	@GetMapping(value = { "/info" })
	public ResponseRet info() {
		String username = SecurityUtils.getUser().getUsername();
		SysUser user = userService.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getUsername, username));
		if (user == null) {
			return ResponseRet.failed("获取当前用户信息失败");
		}
		return ResponseRet.ok(userService.getUserInfo(user));
	}

	/**
	 * 获取指定用户全部信息
	 * @return 用户信息
	 */
	@Inner
	@GetMapping("/info/{username}")
	public ResponseRet info(@PathVariable String username) {
		SysUser user = userService.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getUsername, username));
		if (user == null) {
			return ResponseRet.failed(String.format("用户信息为空 %s", username));
		}
		return ResponseRet.ok(userService.getUserInfo(user));
	}

	/**
	 * 通过ID查询用户信息
	 * @param id ID
	 * @return 用户信息
	 */
	@GetMapping("/{id}")
	public ResponseRet user(@PathVariable Integer id) {
		return ResponseRet.ok(userService.getUserVoById(id));
	}

	/**
	 * 根据用户名查询用户信息
	 * @param username 用户名
	 * @return
	 */
	@GetMapping("/details/{username}")
	public ResponseRet user(@PathVariable String username) {
		SysUser condition = new SysUser();
		condition.setUsername(username);
		return ResponseRet.ok(userService.getOne(new QueryWrapper<>(condition)));
	}

	/**
	 * 删除用户信息
	 * @param id ID
	 * @return R
	 */
	@SysLog("删除用户信息")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_user_del')")
	public ResponseRet userDel(@PathVariable Integer id) {
		SysUser sysUser = userService.getById(id);
		return ResponseRet.ok(userService.removeUserById(sysUser));
	}

	/**
	 * 添加用户
	 * @param userDto 用户信息
	 * @return success/false
	 */
	@SysLog("添加用户")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_user_add')")
	public ResponseRet user(@RequestBody UserDTO userDto) {
		return ResponseRet.ok(userService.saveUser(userDto));
	}

	/**
	 * 更新用户信息
	 * @param userDto 用户信息
	 * @return R
	 */
	@SysLog("更新用户信息")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys_user_edit')")
	public ResponseRet updateUser(@Valid @RequestBody UserDTO userDto) {
		return ResponseRet.ok(userService.updateUser(userDto));
	}

	/**
	 * 分页查询用户
	 * @param page 参数集
	 * @param userDTO 查询参数列表
	 * @return 用户集合
	 */
	@GetMapping("/page")
	public ResponseRet getUserPage(Page page, UserDTO userDTO) {
		return ResponseRet.ok(userService.getUserWithRolePage(page, userDTO));
	}

	/**
	 * 修改个人信息
	 * @param userDto userDto
	 * @return success/false
	 */
	@SysLog("修改个人信息")
	@PutMapping("/edit")
	public ResponseRet updateUserInfo(@Valid @RequestBody UserDTO userDto) {
		return userService.updateUserInfo(userDto);
	}

	/**
	 * @param username 用户名称
	 * @return 上级部门用户列表
	 */
	@GetMapping("/ancestor/{username}")
	public ResponseRet listAncestorUsers(@PathVariable String username) {
		return ResponseRet.ok(userService.listAncestorUsersByUsername(username));
	}

}
