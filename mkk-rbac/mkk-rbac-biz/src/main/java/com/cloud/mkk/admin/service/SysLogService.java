package com.cloud.mkk.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.mkk.admin.api.dto.SysLogDTO;
import com.cloud.mkk.admin.api.entity.SysLog;

/**
 * 日志表 服务类
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
public interface SysLogService extends IService<SysLog> {

	/**
	 * 分页查询日志
	 * @param page
	 * @param sysLog
	 * @return
	 */
	Page getLogByPage(Page page, SysLogDTO sysLog);

}
