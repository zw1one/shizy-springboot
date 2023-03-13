package com.example.common.config;

import com.example.common.config.interceptor.RequestParamInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 用WebMvcConfigurationSupport会导致get请求丢失 勿用
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 全局参数解析器，会优先调用@RequestBody的解析器，不适用。
     */
//    @Deprecated
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        argumentResolvers.add(new GlobalArgumentResolver());
//        super.addArgumentResolvers(argumentResolvers);
//    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //从上往下就是执行顺序
//        registry.addInterceptor(new RequestParamInterceptor());
    }

}
