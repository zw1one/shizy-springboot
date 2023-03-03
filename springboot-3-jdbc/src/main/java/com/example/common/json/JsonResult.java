package com.example.common.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.ALWAYS)//在返回的json中，保留null
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code = 200;

    private String msg;

    private T data;

    public static <T> JsonResult<T> success() {
        return success(null);
    }

    public static <T> JsonResult<T> success(T data) {
        return new JsonResult<T>(200, "success", data);
    }

    public static <T> JsonResult<T> fail(String msg) {
        return new JsonResult<T>(500, msg, null);
    }

    public static <T> JsonResult<T> fail() {
        return new JsonResult<T>(500, "server process error!", null);
    }

}











