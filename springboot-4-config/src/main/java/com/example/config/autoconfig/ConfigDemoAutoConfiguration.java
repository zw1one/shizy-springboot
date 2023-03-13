package com.example.config.autoconfig;

import com.example.controller.ConfigHelloTemplet;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//在依赖的jar包中，需在jar包的resources/META-INF/spring.factories中指定该类才能被springboot加载。在本类中不用
@ConditionalOnClass(ConfigHelloTemplet.class)//当ConditionalOnClass中的类被加载时，HelloDemoAutoConfiguration才被加载
@EnableConfigurationProperties(ConfigDemoProperties.class)
public class ConfigDemoAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(ConfigHelloTemplet.class)//如果用户未自定义相关bean，生成默认bean
    public ConfigHelloTemplet configHelloService(ConfigDemoProperties configDemoProperties) {
        return new ConfigHelloTemplet(configDemoProperties.getMsg());
    }
}
