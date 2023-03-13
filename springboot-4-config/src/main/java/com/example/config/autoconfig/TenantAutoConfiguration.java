package com.example.config.autoconfig;

import com.example.config.mybatis.plugins.TenantInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "config.tenant.enable", havingValue = "true")
@EnableConfigurationProperties(TenantProperties.class)
public class TenantAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(TenantInterceptor.class)
    public TenantInterceptor tenantInterceptor(TenantProperties tenantProperties) {
        return new TenantInterceptor();
    }
}
