package com.serhat.votingsystem.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return   httpSecurity.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login.html").permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2-> oauth2
                        .jwt(Customizer.withDefaults())
                ).build();
    }
}