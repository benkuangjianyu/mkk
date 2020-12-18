package com.cloud.mkk.admin.api.dto;

import com.cloud.mkk.admin.api.entity.SysUser;
import lombok.Data;

import java.io.Serializable;

/**
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Data
public class UserInfo implements Serializable {

	/**
	 * 用户基本信息
	 */
	private SysUser sysUser;

	/**
	 * 权限标识集合
	 */
	private String[] permissions;

	/**
	 * 角色集合
	 */
	private Integer[] roles;

}
