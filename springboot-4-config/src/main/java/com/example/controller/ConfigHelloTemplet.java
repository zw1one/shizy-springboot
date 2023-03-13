package com.example.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfigHelloTemplet {

    private String msg;

    public String sayHello() {
        return "Hello, " + msg;
    }
}






