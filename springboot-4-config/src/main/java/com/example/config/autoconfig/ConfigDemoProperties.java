package com.example.config.autoconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "config.demo")
@Data
public class ConfigDemoProperties {
    private String msg = "no hello";
}
