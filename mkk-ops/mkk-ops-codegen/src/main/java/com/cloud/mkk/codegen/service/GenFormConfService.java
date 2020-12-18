package com.cloud.mkk.codegen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.mkk.codegen.entity.GenFormConf;

/**
 * 表单管理
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
public interface GenFormConfService extends IService<GenFormConf> {

	/**
	 * 获取表单信息
	 * @param dsName 数据源ID
	 * @param tableName 表名称
	 * @return
	 */
	String getForm(String dsName, String tableName);

}
