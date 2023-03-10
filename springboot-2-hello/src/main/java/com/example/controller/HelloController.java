package com.example.controller;

import com.example.entity.UserVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Tag(name = "user")
public class HelloController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "Hello Spring Boot 2.0!";
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @Operation(summary  = "user add", description = "新增用户")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public UserVo addUser(@RequestBody UserVo user) {
        return user;
    }

    @GetMapping("/log")
    public void log(String str) {
        log.info("11{}", "a");
    }
}