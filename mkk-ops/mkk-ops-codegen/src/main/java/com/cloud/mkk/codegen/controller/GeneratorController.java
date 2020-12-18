package com.cloud.mkk.codegen.controller;

import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.mkk.codegen.entity.GenConfig;
import com.cloud.mkk.codegen.service.GeneratorService;
import com.cloud.mkk.common.core.util.ResponseRet;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 代码生成器
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/generator")
@Api(value = "generator", tags = "代码生成模块")
public class GeneratorController {

	private final GeneratorService generatorService;

	/**
	 * 列表
	 * @param tableName 参数集
	 * @param dsName 数据源编号
	 * @return 数据库表
	 */
	@GetMapping("/page")
	public ResponseRet getPage(Page page, String tableName, String dsName) {
		return ResponseRet.ok(generatorService.getPage(page, tableName, dsName));
	}

	/**
	 * 预览代码
	 * @param genConfig 数据表配置
	 * @return
	 */
	@GetMapping("/preview")
	public ResponseRet previewCode(GenConfig genConfig) {
		return ResponseRet.ok(generatorService.previewCode(genConfig));
	}

	/**
	 * 生成代码
	 */
	@SneakyThrows
	@PostMapping("/code")
	public void generatorCode(@RequestBody GenConfig genConfig, HttpServletResponse response) {
		byte[] data = generatorService.generatorCode(genConfig);
		response.reset();
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
				String.format("attachment; filename=%s.zip", genConfig.getTableName()));
		response.addHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(data.length));
		response.setContentType("application/octet-stream; charset=UTF-8");

		IoUtil.write(response.getOutputStream(), Boolean.TRUE, data);
	}

}
