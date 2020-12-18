package com.cloud.mkk.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.mkk.admin.api.entity.SysOauthClientDetails;
import com.cloud.mkk.admin.service.SysOauthClientDetailsService;
import com.cloud.mkk.common.core.util.ResponseRet;
import com.cloud.mkk.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 客户端管理模块
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
@Api(value = "client", tags = "客户端管理模块")
public class OauthClientDetailsController {

	private final SysOauthClientDetailsService sysOauthClientDetailsService;

	/**
	 * 通过ID查询
	 * @param id clientId
	 * @return SysOauthClientDetails
	 */
	@GetMapping("/{id}")
	public ResponseRet getById(@PathVariable String id) {
		return ResponseRet.ok(sysOauthClientDetailsService.getById(id));
	}

	/**
	 * 简单分页查询
	 * @param page 分页对象
	 * @param sysOauthClientDetails 系统终端
	 * @return
	 */
	@GetMapping("/page")
	public ResponseRet getOauthClientDetailsPage(Page page, SysOauthClientDetails sysOauthClientDetails) {
		return ResponseRet.ok(sysOauthClientDetailsService.page(page, Wrappers.query(sysOauthClientDetails)));
	}

	/**
	 * 添加
	 * @param sysOauthClientDetails 实体
	 * @return success/false
	 */
	@SysLog("添加终端")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_client_add')")
	public ResponseRet add(@Valid @RequestBody SysOauthClientDetails sysOauthClientDetails) {
		return ResponseRet.ok(sysOauthClientDetailsService.save(sysOauthClientDetails));
	}

	/**
	 * 删除
	 * @param id ID
	 * @return success/false
	 */
	@SysLog("删除终端")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_client_del')")
	public ResponseRet removeById(@PathVariable String id) {
		return ResponseRet.ok(sysOauthClientDetailsService.removeClientDetailsById(id));
	}

	/**
	 * 编辑
	 * @param sysOauthClientDetails 实体
	 * @return success/false
	 */
	@SysLog("编辑终端")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys_client_edit')")
	public ResponseRet update(@Valid @RequestBody SysOauthClientDetails sysOauthClientDetails) {
		return ResponseRet.ok(sysOauthClientDetailsService.updateClientDetailsById(sysOauthClientDetails));
	}

}
