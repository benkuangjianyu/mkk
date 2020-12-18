package com.cloud.mkk.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.mkk.admin.api.entity.SysDict;
import com.cloud.mkk.admin.api.entity.SysDictItem;
import com.cloud.mkk.admin.service.SysDictService;
import com.cloud.mkk.admin.mapper.SysDictItemMapper;
import com.cloud.mkk.admin.mapper.SysDictMapper;
import com.cloud.mkk.common.core.constant.CacheConstants;
import com.cloud.mkk.common.core.constant.enums.DictTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * 字典表
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Service
@RequiredArgsConstructor
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

	private final SysDictItemMapper dictItemMapper;

	/**
	 * 根据ID 删除字典
	 * @param id 字典ID
	 * @return
	 */
	@Override
	@CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
	@Transactional(rollbackFor = Exception.class)
	public void removeDict(Integer id) {
		SysDict dict = this.getById(id);
		// 系统内置
		Assert.state(!DictTypeEnum.SYSTEM.getType().equals(dict.getSystem()), "系统内置字典项目不能删除");
		baseMapper.deleteById(id);
		dictItemMapper.delete(Wrappers.<SysDictItem>lambdaQuery().eq(SysDictItem::getDictId, id));
	}

	/**
	 * 更新字典
	 * @param dict 字典
	 * @return
	 */
	@Override
	public void updateDict(SysDict dict) {
		SysDict sysDict = this.getById(dict.getId());
		// 系统内置
		Assert.state(!DictTypeEnum.SYSTEM.getType().equals(sysDict.getSystem()), "系统内置字典项目不能修改");
		this.updateById(dict);
	}

}
