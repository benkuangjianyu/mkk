package com.cloud.mkk.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.mkk.admin.api.entity.SysDept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 部门管理 Mapper 接口
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Mapper
public interface SysDeptMapper extends BaseMapper<SysDept> {

	/**
	 * 关联dept——relation
	 * @return 数据列表
	 */
	List<SysDept> listDepts();

}
