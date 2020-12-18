package com.cloud.mkk.admin.api.feign.fallback;

import com.cloud.mkk.admin.api.entity.SysLog;
import com.cloud.mkk.admin.api.feign.RemoteLogService;
import com.cloud.mkk.common.core.util.ResponseRet;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Slf4j
@Component
public class RemoteLogServiceFallbackImpl implements RemoteLogService {

	@Setter
	private Throwable cause;

	/**
	 * 保存日志
	 * @param sysLog 日志实体
	 * @param from 内部调用标志
	 * @return succes、false
	 */
	@Override
	public ResponseRet<Boolean> saveLog(SysLog sysLog, String from) {
		log.error("feign 插入日志失败", cause);
		return null;
	}

}
