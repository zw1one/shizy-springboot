package com.example.config.autoconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "config.tenant")
@Data
public class TenantProperties {
    private boolean enable = false;
}
