package com.example.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.json.JsonResult;
import com.example.common.json.PageVo;
import com.example.user.entity.*;
import com.example.user.service.UserService;
import com.example.utils.data.MyBeanUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@Slf4j
@RestController
@RequestMapping("/api")
@Tag(name = "user-demo")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "user query list", description = "")
    @RequestMapping(value = "/user/list", method = RequestMethod.POST)
    public JsonResult<PageVo<UserPo>> queryList(@RequestBody(required = true) UserParamQuery param) {
        Page<UserPo> pageList = userService.queryList(param, param.getPage().getQueryPage());
//        Page<UserPo> pageList = userService.queryListBySQL(param, param.getPage().getQueryPage());
        return JsonResult.success(MyBeanUtils.copyProperties(pageList, new PageVo<>()));
    }

    @Operation(summary = "user query detail", description = "")
//    @RequestMapping(value = "/user/{userId}", method = RequestMethod.POST)
//    public JsonResult<UserVo> queryDetail(@Valid @RequestBody(required = true) UserParamId param) {
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public JsonResult<UserVo> queryDetail(@Valid UserParamId param) {
        UserVo userVo = userService.queryDetail(param.getUserId());
        return JsonResult.success(userVo);
    }

    @Operation(summary = "user add", description = "")
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public JsonResult add(@Valid @RequestBody UserParamAdd param) {
        return JsonResult.success(userService.add(param));
    }

    @Operation(summary = "user update", description = "")
    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public JsonResult update(@Valid @RequestBody UserParamUpdate param) {
        return JsonResult.success(userService.updateById(param));
    }

    @Operation(summary = "user delete", description = "")
    @RequestMapping(value = "/user/delete", method = RequestMethod.POST)
    public JsonResult delete(@Valid @RequestBody UserParamId param) {
        return JsonResult.success(userService.delete(param.getUserId()));
    }

    @Operation(summary = "user deleteBatch", description = "")
    @RequestMapping(value = "/user/deleteBatch", method = RequestMethod.POST)
    public JsonResult deleteBatch(@Valid @RequestBody UserParamIds param) {
        return JsonResult.success(userService.deleteBatch(param.getUserIds()));
    }

}










