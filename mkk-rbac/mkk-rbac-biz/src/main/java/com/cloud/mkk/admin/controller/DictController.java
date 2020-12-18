package com.cloud.mkk.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.mkk.admin.api.entity.SysDict;
import com.cloud.mkk.admin.api.entity.SysDictItem;
import com.cloud.mkk.admin.service.SysDictItemService;
import com.cloud.mkk.admin.service.SysDictService;
import com.cloud.mkk.common.core.constant.CacheConstants;
import com.cloud.mkk.common.core.util.RedisUtil;
import com.cloud.mkk.common.core.util.ResponseRet;
import com.cloud.mkk.common.log.annotation.SysLog;
import com.cloud.mkk.common.security.util.SecurityUtils;
import com.cloud.mkk.config.api.feign.ParamFeign;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 字典表 前端控制器
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/dict")
@Api(value = "dict", tags = "字典管理模块")
public class DictController {

	private final SysDictItemService sysDictItemService;

	private final SysDictService sysDictService;

	private final ParamFeign paramFeign;

	private final RedisUtil redisUtil;

	/**
	 * 通过ID查询字典信息
	 * @param id ID
	 * @return 字典信息
	 */
	@GetMapping("/{id}")
	public ResponseRet getById(@PathVariable Integer id) {
		return ResponseRet.ok(sysDictService.getById(id));
	}

	/**
	 * 分页查询字典信息
	 * @param page 分页对象
	 * @return 分页对象
	 */
	@GetMapping("/page")
	public ResponseRet<IPage> getDictPage(Page page, SysDict sysDict) {
		String uroot = paramFeign.get("uploadRoot");
		System.out.println("uroot========================"+uroot+ SecurityUtils.getUser().getFullname());
		return ResponseRet.ok(sysDictService.page(page, Wrappers.query(sysDict)));
	}

	/**
	 * 通过字典类型查找字典
	 * @param type 类型
	 * @return 同类型字典
	 */
	@GetMapping("/type/{type}")
	@Cacheable(value = CacheConstants.DICT_DETAILS, key = "#type")
	public ResponseRet getDictByType(@PathVariable String type) {
		return ResponseRet.ok(sysDictItemService.list(Wrappers.<SysDictItem>query().lambda().eq(SysDictItem::getType, type)));
	}

	/**
	 * 添加字典
	 * @param sysDict 字典信息
	 * @return success、false
	 */
	@SysLog("添加字典")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_dict_add')")
	public ResponseRet save(@Valid @RequestBody SysDict sysDict) {
		return ResponseRet.ok(sysDictService.save(sysDict));
	}

	/**
	 * 删除字典，并且清除字典缓存
	 * @param id ID
	 * @return R
	 */
	@SysLog("删除字典")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_dict_del')")
	public ResponseRet removeById(@PathVariable Integer id) {
		sysDictService.removeDict(id);
		return ResponseRet.ok();
	}

	/**
	 * 修改字典
	 * @param sysDict 字典信息
	 * @return success/false
	 */
	@PutMapping
	@SysLog("修改字典")
	@PreAuthorize("@pms.hasPermission('sys_dict_edit')")
	public ResponseRet updateById(@Valid @RequestBody SysDict sysDict) {
		sysDictService.updateDict(sysDict);
		return ResponseRet.ok();
	}

	/**
	 * 分页查询
	 * @param page 分页对象
	 * @param sysDictItem 字典项
	 * @return
	 */
	@GetMapping("/item/page")
	public ResponseRet getSysDictItemPage(Page page, SysDictItem sysDictItem) {
		return ResponseRet.ok(sysDictItemService.page(page, Wrappers.query(sysDictItem)));
	}

	/**
	 * 通过id查询字典项
	 * @param id id
	 * @return R
	 */
	@GetMapping("/item/{id}")
	public ResponseRet getDictItemById(@PathVariable("id") Integer id) {
		return ResponseRet.ok(sysDictItemService.getById(id));
	}

	/**
	 * 新增字典项
	 * @param sysDictItem 字典项
	 * @return R
	 */
	@SysLog("新增字典项")
	@PostMapping("/item")
	@CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
	public ResponseRet save(@RequestBody SysDictItem sysDictItem) {
		return ResponseRet.ok(sysDictItemService.save(sysDictItem));
	}

	/**
	 * 修改字典项
	 * @param sysDictItem 字典项
	 * @return R
	 */
	@SysLog("修改字典项")
	@PutMapping("/item")
	public ResponseRet updateById(@RequestBody SysDictItem sysDictItem) {
		return sysDictItemService.updateDictItem(sysDictItem);
	}

	/**
	 * 通过id删除字典项
	 * @param id id
	 * @return R
	 */
	@SysLog("删除字典项")
	@DeleteMapping("/item/{id}")
	public ResponseRet removeDictItemById(@PathVariable Integer id) {
		return sysDictItemService.removeDictItem(id);
	}

}
