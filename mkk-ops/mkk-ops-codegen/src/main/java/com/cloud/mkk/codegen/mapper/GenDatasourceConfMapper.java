package com.cloud.mkk.codegen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.mkk.codegen.entity.GenDatasourceConf;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据源表
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Mapper
public interface GenDatasourceConfMapper extends BaseMapper<GenDatasourceConf> {

}
