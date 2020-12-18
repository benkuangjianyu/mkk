package com.cloud.mkk.common.log.aspect;

import com.cloud.mkk.common.log.event.SysLogEvent;
import com.cloud.mkk.common.log.util.LogTypeEnum;
import com.cloud.mkk.common.log.util.SysLogUtils;
import com.cloud.mkk.admin.api.entity.SysLog;
import com.cloud.mkk.common.core.util.SpringContextHolder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * 操作日志使用spring event异步入库
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Aspect
@Slf4j
public class SysLogAspect {

	@Around("@annotation(sysLog)")
	@SneakyThrows
	public Object around(ProceedingJoinPoint point, com.cloud.mkk.common.log.annotation.SysLog sysLog) {
		String strClassName = point.getTarget().getClass().getName();
		String strMethodName = point.getSignature().getName();
		log.debug("[类名]:{},[方法]:{}", strClassName, strMethodName);

		SysLog logVo = SysLogUtils.getSysLog();
		logVo.setTitle(sysLog.value());

		// 发送异步日志事件
		Long startTime = System.currentTimeMillis();
		Object obj;

		try {
			obj = point.proceed();
		}
		catch (Exception e) {
			logVo.setType(LogTypeEnum.ERROR.getType());
			logVo.setException(e.getMessage());
			throw e;
		}
		finally {
			Long endTime = System.currentTimeMillis();
			logVo.setTime(endTime - startTime);
			SpringContextHolder.publishEvent(new SysLogEvent(logVo));
		}

		return obj;
	}

}
