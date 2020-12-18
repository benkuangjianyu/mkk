package com.cloud.mkk.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloud.mkk.admin.api.entity.SysDept;
import com.cloud.mkk.admin.service.SysDeptService;
import com.cloud.mkk.common.core.util.ResponseRet;
import com.cloud.mkk.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * 部门管理 前端控制器
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/dept")
@Api(value = "dept", tags = "部门管理模块")
public class DeptController {

	private final SysDeptService sysDeptService;

	/**
	 * 通过ID查询
	 * @param id ID
	 * @return SysDept
	 */
	@GetMapping("/{id}")
	public ResponseRet getById(@PathVariable Integer id) {
		return ResponseRet.ok(sysDeptService.getById(id));
	}

	/**
	 * 返回树形菜单集合
	 * @return 树形菜单
	 */
	@GetMapping(value = "/tree")
	public ResponseRet listDeptTrees() {
		return ResponseRet.ok(sysDeptService.listDeptTrees());
	}

	/**
	 * 返回当前用户树形菜单集合
	 * @return 树形菜单
	 */
	@GetMapping(value = "/user-tree")
	public ResponseRet listCurrentUserDeptTrees() {
		return ResponseRet.ok(sysDeptService.listCurrentUserDeptTrees());
	}

	/**
	 * 添加
	 * @param sysDept 实体
	 * @return success/false
	 */
	@SysLog("添加部门")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_dept_add')")
	public ResponseRet save(@Valid @RequestBody SysDept sysDept) {
		return ResponseRet.ok(sysDeptService.saveDept(sysDept));
	}

	/**
	 * 删除
	 * @param id ID
	 * @return success/false
	 */
	@SysLog("删除部门")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_dept_del')")
	public ResponseRet removeById(@PathVariable Integer id) {
		return ResponseRet.ok(sysDeptService.removeDeptById(id));
	}

	/**
	 * 编辑
	 * @param sysDept 实体
	 * @return success/false
	 */
	@SysLog("编辑部门")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys_dept_edit')")
	public ResponseRet update(@Valid @RequestBody SysDept sysDept) {
		sysDept.setUpdateTime(LocalDateTime.now());
		return ResponseRet.ok(sysDeptService.updateDeptById(sysDept));
	}

	/**
	 * 根据部门名查询部门信息
	 * @param deptname 部门名
	 * @return
	 */
	@GetMapping("/details/{deptname}")
	public ResponseRet user(@PathVariable String deptname) {
		SysDept condition = new SysDept();
		condition.setName(deptname);
		return ResponseRet.ok(sysDeptService.getOne(new QueryWrapper<>(condition)));
	}

}
