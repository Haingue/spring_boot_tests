package com.example.test.reactive.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync(proxyTargetClass = true)
public class MainConfiguration {
}
