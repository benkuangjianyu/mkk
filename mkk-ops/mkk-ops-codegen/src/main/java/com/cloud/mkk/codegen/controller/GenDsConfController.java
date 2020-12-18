package com.cloud.mkk.codegen.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.mkk.codegen.entity.GenDatasourceConf;
import com.cloud.mkk.codegen.service.GenDatasourceConfService;
import com.cloud.mkk.common.core.util.ResponseRet;
import com.cloud.mkk.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 数据源管理
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/dsconf")
@Api(value = "dsconf", tags = "数据源管理模块")
public class GenDsConfController {

	private final GenDatasourceConfService datasourceConfService;

	/**
	 * 分页查询
	 * @param page 分页对象
	 * @param datasourceConf 数据源表
	 * @return
	 */
	@GetMapping("/page")
	public ResponseRet getSysDatasourceConfPage(Page page, GenDatasourceConf datasourceConf) {
		return ResponseRet.ok(datasourceConfService.page(page, Wrappers.query(datasourceConf)));
	}

	/**
	 * 查询全部数据源
	 * @return
	 */
	@GetMapping("/list")
	public ResponseRet list() {
		return ResponseRet.ok(datasourceConfService.list());
	}

	/**
	 * 通过id查询数据源表
	 * @param id id
	 * @return R
	 */
	@GetMapping("/{id}")
	public ResponseRet getById(@PathVariable("id") Integer id) {
		return ResponseRet.ok(datasourceConfService.getById(id));
	}

	/**
	 * 新增数据源表
	 * @param datasourceConf 数据源表
	 * @return R
	 */
	@SysLog("新增数据源表")
	@PostMapping
	public ResponseRet save(@RequestBody GenDatasourceConf datasourceConf) {
		return ResponseRet.ok(datasourceConfService.saveDsByEnc(datasourceConf));
	}

	/**
	 * 修改数据源表
	 * @param conf 数据源表
	 * @return R
	 */
	@SysLog("修改数据源表")
	@PutMapping
	public ResponseRet updateById(@RequestBody GenDatasourceConf conf) {
		return ResponseRet.ok(datasourceConfService.updateDsByEnc(conf));
	}

	/**
	 * 通过id删除数据源表
	 * @param id id
	 * @return R
	 */
	@SysLog("删除数据源表")
	@DeleteMapping("/{id}")
	public ResponseRet removeById(@PathVariable Integer id) {
		return ResponseRet.ok(datasourceConfService.removeByDsId(id));
	}

}
