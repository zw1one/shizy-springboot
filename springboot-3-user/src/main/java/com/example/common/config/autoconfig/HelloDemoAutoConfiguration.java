package com.example.common.config.autoconfig;

import com.example.user.service.impl.DemoHelloAutoConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//在依赖的jar包中，需在jar包的resources/META-INF/spring.factories中指定该类才能被springboot加载。在本类中不用
@ConditionalOnClass(DemoHelloAutoConfig.class)//当ConditionalOnClass中的类被加载时，HelloDemoAutoConfiguration才被加载
public class HelloDemoAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(DemoHelloAutoConfig.class)//如果用户未自定义相关bean，生成默认bean
    public DemoHelloAutoConfig demoHello() {
        return new DemoHelloAutoConfig();
    }
}
