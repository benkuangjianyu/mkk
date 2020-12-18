package com.cloud.mkk.codegen.entity;

import lombok.Data;

import java.util.List;

/**
 * 表属性
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Data
public class TableEntity {

	/**
	 * 名称
	 */
	private String tableName;

	/**
	 * 备注
	 */
	private String comments;

	/**
	 * 主键
	 */
	private ColumnEntity pk;

	/**
	 * 列名
	 */
	private List<ColumnEntity> columns;

	/**
	 * 驼峰类型
	 */
	private String caseClassName;

	/**
	 * 普通类型
	 */
	private String lowerClassName;

}
