package com.example.common.json;

public enum JsonResultEnum {
    SUCCESS(200, "请求成功"),
    FAIL(500, "服务器异常!"),
    PARAMETER_ERROR(501, "请求参数有误!");

    private final Integer code;
    private final String message;

    JsonResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
