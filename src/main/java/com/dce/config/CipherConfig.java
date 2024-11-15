package com.dce.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "cipher")
public class CipherConfig {
    private String algorithm;
    private String mode;
    private String padding;
}
