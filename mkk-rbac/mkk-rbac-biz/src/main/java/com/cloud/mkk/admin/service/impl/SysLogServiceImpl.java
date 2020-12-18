package com.cloud.mkk.admin.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.mkk.admin.api.dto.SysLogDTO;
import com.cloud.mkk.admin.api.entity.SysLog;
import com.cloud.mkk.admin.service.SysLogService;
import com.cloud.mkk.admin.mapper.SysLogMapper;
import org.springframework.stereotype.Service;

/**
 * 日志表 服务实现类
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

	@Override
	public Page getLogByPage(Page page, SysLogDTO sysLog) {
		LambdaQueryWrapper<SysLog> wrapper = Wrappers.lambdaQuery();
		if (StrUtil.isNotBlank(sysLog.getType())) {
			wrapper.eq(SysLog::getType, sysLog.getType());
		}

		if (ArrayUtil.isNotEmpty(sysLog.getCreateTime())) {
			wrapper.ge(SysLog::getCreateTime, sysLog.getCreateTime()[0]).le(SysLog::getCreateTime,
					sysLog.getCreateTime()[1]);
		}

		return baseMapper.selectPage(page, wrapper);
	}

}
