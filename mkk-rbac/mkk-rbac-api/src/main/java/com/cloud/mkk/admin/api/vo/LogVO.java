package com.cloud.mkk.admin.api.vo;

import com.cloud.mkk.admin.api.entity.SysLog;
import lombok.Data;

import java.io.Serializable;

/**
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Data
public class LogVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private SysLog sysLog;

	private String username;

}
