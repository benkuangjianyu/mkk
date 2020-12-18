package com.cloud.mkk.admin.api.dto;

import com.cloud.mkk.admin.api.entity.SysRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色Dto
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDTO extends SysRole {

	/**
	 * 角色部门Id
	 */
	private Integer roleDeptId;

	/**
	 * 部门名称
	 */
	private String deptName;

}
