package com.dce.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class KeyConfig {
    @Value("${key.public.path}")
    private String publicKeyPath;
    @Value("${key.private.path}")
    private String privateKeyPath;
}
