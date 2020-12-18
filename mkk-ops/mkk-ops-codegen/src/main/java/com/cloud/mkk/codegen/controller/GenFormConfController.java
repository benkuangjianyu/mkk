package com.cloud.mkk.codegen.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.mkk.codegen.entity.GenFormConf;
import com.cloud.mkk.codegen.service.GenFormConfService;
import com.cloud.mkk.common.core.util.ResponseRet;
import com.cloud.mkk.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 表单管理
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/form")
@Api(value = "form", tags = "表单管理")
public class GenFormConfController {

	private final GenFormConfService genRecordService;

	/**
	 * 分页查询
	 * @param page 分页对象
	 * @param formConf 生成记录
	 * @return
	 */
	@ApiOperation(value = "分页查询", notes = "分页查询")
	@GetMapping("/page")
	public ResponseRet getGenFormConfPage(Page page, GenFormConf formConf) {
		return ResponseRet.ok(genRecordService.page(page, Wrappers.query(formConf)));
	}

	/**
	 * 通过id查询生成记录
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "通过id查询", notes = "通过id查询")
	@GetMapping("/{id}")
	public ResponseRet getById(@PathVariable("id") Integer id) {
		return ResponseRet.ok(genRecordService.getById(id));
	}

	/**
	 * 通过id查询生成记录
	 * @param dsName 数据源ID
	 * @param tableName tableName
	 * @return R
	 */
	@ApiOperation(value = "通过tableName查询表单信息")
	@GetMapping("/info")
	public ResponseRet form(String dsName, String tableName) {
		return ResponseRet.ok(genRecordService.getForm(dsName, tableName));
	}

	/**
	 * 新增生成记录
	 * @param formConf 生成记录
	 * @return R
	 */
	@ApiOperation(value = "新增生成记录", notes = "新增生成记录")
	@SysLog("新增生成记录")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('gen_form_add')")
	public ResponseRet save(@RequestBody GenFormConf formConf) {
		return ResponseRet.ok(genRecordService.save(formConf));
	}

	/**
	 * 通过id删除生成记录
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "通过id删除生成记录", notes = "通过id删除生成记录")
	@SysLog("通过id删除生成记录")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('gen_form_del')")
	public ResponseRet removeById(@PathVariable Integer id) {
		return ResponseRet.ok(genRecordService.removeById(id));
	}

}
