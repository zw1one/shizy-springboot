package com.example.user.controller;

import com.example.utils.data.MyRedisUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/redis")
@Tag(name = "redis")
public class RedisController {

    @Autowired
    private MyRedisUtil myRedisUtil;

    @GetMapping("/set")
    public String set(String str) {
        myRedisUtil.set("str", str);
        return null;
    }

    @GetMapping("/get")
    public String get(String key) {
        return (String) myRedisUtil.get("str");
    }


}










