package com.example.utils.bean;

import org.springframework.beans.BeanUtils;

public class MyBeanUtils {
    public static <T> T copyProperties(Object source, T target) {
        BeanUtils.copyProperties(source, target);
        return target;
    }
}
