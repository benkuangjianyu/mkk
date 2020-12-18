package com.cloud.mkk.admin.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门树
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeptTree extends TreeNode {

	private String name;

}
