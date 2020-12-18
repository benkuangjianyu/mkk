package com.cloud.mkk.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.mkk.admin.api.entity.SysDictItem;
import com.cloud.mkk.common.core.util.ResponseRet;

/**
 * 字典项
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
public interface SysDictItemService extends IService<SysDictItem> {

	/**
	 * 删除字典项
	 * @param id 字典项ID
	 * @return
	 */
	ResponseRet removeDictItem(Integer id);

	/**
	 * 更新字典项
	 * @param item 字典项
	 * @return
	 */
	ResponseRet updateDictItem(SysDictItem item);

}
