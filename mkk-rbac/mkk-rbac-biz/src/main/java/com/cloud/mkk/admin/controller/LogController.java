package com.cloud.mkk.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.mkk.admin.api.dto.SysLogDTO;
import com.cloud.mkk.admin.api.entity.SysLog;
import com.cloud.mkk.admin.service.SysLogService;
import com.cloud.mkk.common.core.util.ResponseRet;
import com.cloud.mkk.common.security.annotation.Inner;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 日志表 前端控制器
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/log")
@Api(value = "log", tags = "日志管理模块")
public class LogController {

	private final SysLogService sysLogService;

	/**
	 * 简单分页查询
	 * @param page 分页对象
	 * @param sysLog 系统日志
	 * @return
	 */
	@GetMapping("/page")
	public ResponseRet getLogPage(Page page, SysLogDTO sysLog) {
		return ResponseRet.ok(sysLogService.getLogByPage(page, sysLog));
	}

	/**
	 * 删除日志
	 * @param id ID
	 * @return success/false
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_log_del')")
	public ResponseRet removeById(@PathVariable Long id) {
		return ResponseRet.ok(sysLogService.removeById(id));
	}

	/**
	 * 插入日志
	 * @param sysLog 日志实体
	 * @return success/false
	 */
	@Inner
	@PostMapping
	public ResponseRet save(@Valid @RequestBody SysLog sysLog) {
		return ResponseRet.ok(sysLogService.save(sysLog));
	}

}
