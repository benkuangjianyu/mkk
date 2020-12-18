package com.cloud.mkk.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.mkk.admin.api.entity.SysDeptRelation;
import org.apache.ibatis.annotations.Mapper;

/**
 * Mapper 接口
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Mapper
public interface SysDeptRelationMapper extends BaseMapper<SysDeptRelation> {

	/**
	 * 删除部门关系表数据
	 * @param id 部门ID
	 */
	void deleteDeptRelationsById(Integer id);

	/**
	 * 更改部分关系表数据
	 * @param deptRelation
	 */
	void updateDeptRelations(SysDeptRelation deptRelation);

}
