package com.example.common.exception;

public class MyExceptionUtil {
    public static void throwException(String msg) {
        throw new MyException(msg);
    }
}
