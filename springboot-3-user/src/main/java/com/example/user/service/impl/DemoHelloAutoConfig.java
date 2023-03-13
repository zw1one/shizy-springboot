package com.example.user.service.impl;


public class DemoHelloAutoConfig {
    private String defaultMsg = "";

    public String sayHello(String msg) {
        return "hello, " + ((msg == null || msg.isEmpty()) ? defaultMsg : msg);
    }

    public void setDefaultMsg(String defaultMsg) {
        this.defaultMsg = defaultMsg;
    }
}






