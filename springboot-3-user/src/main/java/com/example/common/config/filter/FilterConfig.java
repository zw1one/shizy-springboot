package com.example.common.config.filter;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * 过滤器配置
 * <p>
 * 使用@WebFilter注册过滤器，配置不如config方便
 */
@Configuration
public class FilterConfig {

    /**
     * RequestBodyFilter 解决request.getInputStream()只能读一次的问题
     */
    @Bean
    public FilterRegistrationBean requestBodyFilterBean(){
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(1);// 设置优先级
        filterRegistrationBean.setFilter(new RequestBodyFilter());// 绑定过滤器
        return filterRegistrationBean;
    }

    /**
     * requestInfoFilter 打印请求信息
     */
    @Bean
    public FilterRegistrationBean requestInfoFilterBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new RequestInfoFilterFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(2);//order的数值越小 则优先级越高
        return filterRegistrationBean;
    }

}