package com.example.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.exception.MyException;
import com.example.user.entity.*;
import com.example.user.mapper.UserMapper;
import com.example.user.service.UserService;
import com.example.utils.bean.MyBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * <p>
 * 最简单的CRUD
 * </p>
 *
 * @author shizy
 * @since 2023-03-03
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPo> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<UserPo> queryList(UserParamQuery param, Page<UserPo> page) {
        log.info("request: [user] - queryList param={}, page={}", param, page);
        return super.page(page, new LambdaQueryWrapper<UserPo>()
                .eq(Objects.isNull(param.getUserName()), UserPo::getUserName, param.getUserName())
        );
    }

    @Override
    public Page<UserPo> queryListBySQL(UserParamQuery param, Page<UserPo> page) {
        log.info("request: [user] - queryList param={}, page={}", param, page);
        return userMapper.queryListBySQL(param, page);
    }

    @Override
    public UserVo queryDetail(String id) {
        log.info("request: [user] - queryDetail id={}", id);
        UserPo userPo = super.getById(id);
        Optional.ofNullable(userPo).orElseThrow(() -> new MyException("user is null"));
        return MyBeanUtils.copyProperties(userPo, new UserVo());
    }

    @Override
    public UserVo add(UserParamAdd param) {
        log.info("request: [user] - add param={}", param);
        String id = IdWorker.get32UUID();//todo shizy 研究一下id
        UserPo userPo = MyBeanUtils.copyProperties(param, new UserPo());
        userPo.setUserId(id);
        if (!super.save(userPo)) {
            throw new MyException("user save fail");
        }
        return this.queryDetail(id);
    }

    @Override
    public UserVo updateById(UserParamUpdate param) {
        log.info("request: [user] - updateById param={}", param);
        //todo shizy controller层判空id
        UserPo userPo = MyBeanUtils.copyProperties(param, new UserPo());
        if (!super.updateById(userPo)) {
            throw new MyException("user updateById fail");
        }
        return this.queryDetail(param.getUserId());
    }

    @Override
    public boolean delete(String id) {
        log.info("request: [user] - delete id={}", id);
        //todo shizy controller层判空id
        if (!super.removeById(id)) {
            throw new MyException("user removeById fail");
        }
        return true;
    }

    @Override
    public boolean deleteBatch(List<String> ids) {
        log.info("request: [user] - deleteBatch ids={}", ids);
        //todo shizy controller层判空id
        if (!super.removeBatchByIds(ids, 500)) {
            throw new MyException("user removeBatchByIds fail");
        }
        return true;
    }


}







