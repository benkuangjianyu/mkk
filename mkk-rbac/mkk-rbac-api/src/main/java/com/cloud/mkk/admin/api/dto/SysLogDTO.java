package com.cloud.mkk.admin.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 日志查询传输对象
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Data
@ApiModel(value = "日志查询对象")
public class SysLogDTO {

	/**
	 * 查询日志类型
	 */
	@ApiModelProperty(value = "日志类型")
	private String type;

	/**
	 * 创建时间区间 [开始时间，结束时间]
	 */
	@ApiModelProperty(value = "创建时间区间")
	private LocalDateTime[] createTime;

}
