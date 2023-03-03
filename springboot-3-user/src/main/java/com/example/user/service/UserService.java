package com.example.user.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.user.entity.*;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author shizy
 * @since 2019-08-19
 */
public interface UserService extends IService<UserPo> {

    Page<UserPo> queryList(UserParamQuery param, Page<UserPo> page);

    Page<UserPo> queryListBySQL(UserParamQuery param, Page<UserPo> page);

    UserVo queryDetail(String id);

    UserVo add(UserParamAdd po);

    UserVo updateById(UserParamUpdate po);

    boolean delete(String id);

    boolean deleteBatch(List<String> ids);

}



















