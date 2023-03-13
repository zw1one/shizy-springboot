package com.example.user.controller;

import com.example.controller.ConfigHelloTemplet;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;


@Slf4j
@RestController
@RequestMapping("/config")
@Tag(name = "config")
public class AutoConfigController {

    @Autowired
    DataSource dataSource;

    @Autowired
    private ConfigHelloTemplet configHelloTemplet;

    @GetMapping("/hello")
    public String configHello() {
        return configHelloTemplet.sayHello();
    }

}










