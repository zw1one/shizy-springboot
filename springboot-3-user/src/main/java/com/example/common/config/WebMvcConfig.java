package com.example.common.config;

import com.example.common.config.interceptor.RequestParamInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    /**
     * 全局参数解析器，会优先调用@RequestBody的解析器，不适用。
     */
//    @Deprecated
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        argumentResolvers.add(new GlobalArgumentResolver());
//        super.addArgumentResolvers(argumentResolvers);
//    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //从上往下就是执行顺序
//        registry.addInterceptor(new RequestParamInterceptor());
//    }

}
