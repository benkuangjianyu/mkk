package com.cloud.mkk.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.mkk.admin.api.entity.SysLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 日志表 Mapper 接口
 *
 * @author kuangjianyu
 * @date 2020-12-14
 */
@Mapper
public interface SysLogMapper extends BaseMapper<SysLog> {

}
