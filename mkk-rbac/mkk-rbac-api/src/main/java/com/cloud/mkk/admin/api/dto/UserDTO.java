package com.cloud.mkk.admin.api.dto;

import com.cloud.mkk.admin.api.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends SysUser {

	/**
	 * 角色ID
	 */
	private List<Integer> role;

	private Integer deptId;

	/**
	 * 新密码
	 */
	private String newpassword1;

}
