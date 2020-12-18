package com.cloud.mkk.admin.api.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Data
public class TreeNode {

	protected int id;

	protected int parentId;

	protected List<TreeNode> children = new ArrayList<TreeNode>();

	protected Boolean hasChildren;

	public void add(TreeNode node) {
		children.add(node);
	}

}
