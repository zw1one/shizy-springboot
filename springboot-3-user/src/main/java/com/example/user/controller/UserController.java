package com.example.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.json.JsonResult;
import com.example.common.json.PageVo;
import com.example.user.entity.UserParamDetail;
import com.example.user.entity.UserParamQuery;
import com.example.user.entity.UserPo;
import com.example.user.entity.UserVo;
import com.example.user.service.UserService;
import com.example.utils.bean.MyBeanUtils;
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
//        Page<UserPo> pageList = userService.queryList(param, param.getPage().getQueryPage());
        Page<UserPo> pageList = userService.queryListBySQL(param, param.getPage().getQueryPage());
        return JsonResult.success(MyBeanUtils.copyProperties(pageList, new PageVo<>()));
    }

    @Operation(summary = "user query detail", description = "")
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.POST)
    public JsonResult<UserVo> queryDetail(@Valid @RequestBody(required = true) UserParamDetail param) {
        UserVo userVo = userService.queryDetail(param.getUserId());
        return JsonResult.success(userVo);
    }
//
//    @Operation(summary = "user add", description = "")
//    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
//    public JsonResult add(@RequestBody UserDto param) {
//        try {
//            UserPo po = BeanUtil.copyProperties(param, new UserPo());
//            String result = userService.add(po);
//            return JsonResult.success(result);
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//            return JsonResult.fail();
//        }
//    }
//
//    @Operation(summary = "user update", description = "")
//    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
//    public JsonResult add(@RequestBody UserPo param) {
//        try {
//            UserPo po = BeanUtil.copyProperties(param, new UserPo());
//            boolean result = userService.updateById(po);
//            return JsonResult.success(result);
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//            return JsonResult.fail();
//        }
//    }
//
//    @Operation(summary = "user delete", description = "")
//    @RequestMapping(value = "/user/delete", method = RequestMethod.POST)
//    public JsonResult delete(@RequestBody UserDto param) {
//        try {
//            boolean result = userService.delete(param.getUserId());
//            return JsonResult.success(result);
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//            return JsonResult.fail();
//        }
//    }


}
















