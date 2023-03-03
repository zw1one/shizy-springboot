package com.example.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.user.entity.UserParamQuery;
import com.example.user.entity.UserPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author shizy
 * @since 2019-08-19
 */
public interface UserMapper extends BaseMapper<UserPo> {

    Page<UserPo> queryListBySQL(UserParamQuery param, Page<UserPo> page);

}
