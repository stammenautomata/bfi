package com.benjaminstammen.bfi.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
class SecurityConfig {
    @Bean
    fun httpSecurity(http: HttpSecurity): SecurityFilterChain {
        return http.authorizeRequests()
            .anyRequest()
                .permitAll()
            .and()
                .cors()
            .and()
                .build()
    }
}
