package com.haingue.azure.sso;

import com.azure.spring.cloud.autoconfigure.implementation.aad.security.AadResourceServerHttpSecurityConfigurer;
import com.azure.spring.cloud.autoconfigure.implementation.aad.security.AadWebApplicationHttpSecurityConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
@EnableMethodSecurity
public class AadOAuth2LoginSecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.apply(AadWebApplicationHttpSecurityConfigurer.aadWebApplication());
//        http.apply(AadResourceServerHttpSecurityConfigurer.aadResourceServer());
        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                authorizationManagerRequestMatcherRegistry
                        .requestMatchers("/admin/**")
//                            .hasAnyRole("Admin") // not works
                            .hasAnyAuthority("APPROLE_Admin")
                        .requestMatchers("/manager/**")
                            //.hasAnyRole("Manager") // not works
                            .hasAnyAuthority("APPROLE_Manager")
                        .requestMatchers("/public/**", "/").permitAll()
                        .anyRequest().authenticated());
        // Do some custom configuration.
        return http.build();
    }
}