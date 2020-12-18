package com.cloud.mkk.common.log.event;

import com.cloud.mkk.admin.api.entity.SysLog;
import org.springframework.context.ApplicationEvent;

/**
 * 系统日志事件
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
public class SysLogEvent extends ApplicationEvent {

	public SysLogEvent(SysLog source) {
		super(source);
	}

}
